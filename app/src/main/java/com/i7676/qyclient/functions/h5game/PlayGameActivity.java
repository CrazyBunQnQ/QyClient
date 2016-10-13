package com.i7676.qyclient.functions.h5game;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.ReqResult;
import com.i7676.qyclient.entity.WftUnifiedResponseEntity;
import com.i7676.qyclient.functions.login.LoginActivity;
import com.i7676.qyclient.rx.DefaultSubscriber;
import com.i7676.qyclient.util.DialogUtils;
import com.orhanobut.logger.Logger;
import com.switfpass.pay.MainApplication;
import com.switfpass.pay.activity.PayPlugin;
import com.switfpass.pay.bean.RequestMsg;
import java.util.HashMap;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayGameActivity extends AppCompatActivity {

    public static final String GAME_URL = "game_url";

    public static Intent buildIntent(Context ctx, Bundle args) {
        final Intent mIntent = new Intent(ctx, PlayGameActivity.class);
        if (args != null) mIntent.putExtras(args);
        return mIntent;
    }

    private PlayGameAtyComponent atyComponent;
    @Inject YNetApiService mYNetApiService;
    private String targetURL;
    private GameView mGameView;
    private String wftUniqueTransno;

    public static final int TOKEN_OVERDUE = -1;
    public static final int GAME_LOAD_EXCEPTION = -3;
    public static final int FORCE_TO_EXIT = -2;
    public static final int INIT_PAYMENT = -4;
    public static final int WFT_PAY_RESULT_LOAD = -5;
    private ProgressDialog mProgressDialog;
    private AlertDialog mAlertDialog;
    private int dialogWhat = FORCE_TO_EXIT;
    private final Handler UIHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TOKEN_OVERDUE:
                    showAlterDialog("登录已过期，请重新登录再进入游戏.", TOKEN_OVERDUE);
                    break;
                case INIT_PAYMENT:
                    final String payment = (String) msg.obj;
                    preOrderReq(parseURL(payment));
                    break;
                case WFT_PAY_RESULT_LOAD:
                    mProgressDialog.dismiss();
                    final String wftRetURL = (String) msg.obj;
                    mGameView.loadUrl(wftRetURL);
                    break;
                default:
                case GAME_LOAD_EXCEPTION:
                    break;
            }
        }

        private HashMap<String, String> parseURL(String url) {
            final HashMap<String, String> payParams = new HashMap<>();
            payParams.put("token", QyClient.curUser.getToken());
            String paramsStr = url.split("[?]")[1];
            String params[] = paramsStr.split("[&]");
            for (String param : params) {
                String temp[] = param.split("[=]");
                if (temp.length == 2) {
                    payParams.put(temp[0], temp[1]);
                }
            }
            return payParams;
        }
    };

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupInject();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("加载中...");

        Intent data = getIntent();
        targetURL = data.getStringExtra(GAME_URL);

        // 全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mGameView = new GameView(this);
        mGameView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT));
        //mGameView.addJavascriptInterface(new PlayGameActivity.JavaScriptShareDataHandler(),
        //    "HTML_HANDLER");
        mGameView.setWebViewClient(mWebViewClient);
        mGameView.setWebChromeClient(new WebChromeClient());

        setContentView(mGameView);

        if (null == targetURL || "".equals(targetURL)) {
            targetURL = "http://www.baidu.com";
        }

        //targetURL = "www.taobao.com";

        mGameView.loadUrl(targetURL);
    }

    private void setupInject() {
        atyComponent = DaggerPlayGameAtyComponent.builder()
            .qyClientComponent(((QyClient) getApplication()).getClientComponent())
            .build();

        atyComponent.inject(this);
    }

    private void preOrderReq(HashMap<String, String> params) {
        if (params.get("pay_type").equals("9")) { // 支付宝
            mYNetApiService.getZFBOnly(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stringReqResult -> {
                    if (stringReqResult.getRet() == 0) {
                        goAliPay(stringReqResult.getData());
                    }
                }, error -> {
                    Logger.e(">>> error" + error.getMessage());
                });
        } else if (params.get("pay_type").equals("39") || params.get("pay_type")
            .equals("59")) {// 微信
            mYNetApiService.postWFTUnified(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<ReqResult<WftUnifiedResponseEntity>>() {

                    @Override public void onError(Throwable e) {
                        super.onError(e);
                        toast2User("威富通支付失败: 请求统一下单接口失败.");
                    }

                    @Override public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override public void onNext(ReqResult<WftUnifiedResponseEntity> response) {
                        super.onNext(response);
                        if (response.getRet() == ServerConstans.SUCCESS) {
                            final RequestMsg msg = new RequestMsg();
                            msg.setTokenId(response.getData().getToken_id());
                            msg.setTradeType(MainApplication.PAY_WX_WAP);
                            msg.setOutTradeNo((wftUniqueTransno = response.getData().getTransno()));
                            PayPlugin.unifiedH5Pay(PlayGameActivity.this, msg);
                        } else {
                            toast2User("威富通支付失败: 请求统一下单接口失败.");
                        }
                    }
                });
        } else {
            // UNKNOW
        }
    }

    private void toast2User(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void goAliPay(String trade) {
        final PayTask task = new PayTask(this);
        final String ex = task.fetchOrderInfoFromH5PayUrl(trade);
        if (!TextUtils.isEmpty(ex)) {
            System.out.println("paytask:::::" + trade);
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("payTask:::" + ex);
                    final H5PayResultModel result = task.h5Pay(ex, true);
                    if (!TextUtils.isEmpty(result.getReturnUrl())) {
                        PlayGameActivity.this.runOnUiThread(new Runnable() {
                            @Override public void run() {
                                mGameView.loadUrl(result.getReturnUrl());
                            }
                        });
                    }
                }
            }).start();
        }
    }

    private void clearWebViewBackStep() {
        if (mGameView == null) return;
        mGameView.clearHistory();
    }

    //设置网页回退
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mGameView.canGoBack()) {
            mGameView.goBack();
            return true;
        }
        exitConfirm();
        return super.onKeyDown(keyCode, event);
    }

    private void exitConfirm() {
        showAlterDialog("退出游戏并返回主页面？", FORCE_TO_EXIT);
    }

    private void showAlterDialog(String msg, int dialogWhat) {
        this.dialogWhat = dialogWhat;
        if (mAlertDialog == null) {
            mAlertDialog = DialogUtils.showAlert(this, "提示", msg, "确认", confirmListener, "取消",
                confirmListener);
            mAlertDialog.setCancelable(false);
            return;
        }
        mAlertDialog.setMessage(msg);
        mAlertDialog.show();
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String retCode = data.getExtras().getString("resultCode");

        if (requestCode == TOKEN_OVERDUE) {
            if (resultCode == RESULT_OK) {
                int pointIndex = targetURL.indexOf("&token");

                if (pointIndex == -1) {
                    Logger.e(">>> TOKEN CAN NOT BE NULL！");
                    finish();
                    return;
                }
                targetURL = targetURL.substring(0, pointIndex);
                targetURL += "&token=" + QyClient.curUser.getToken();
                mGameView.loadUrl(targetURL);
            }
        } else if (!TextUtils.isEmpty(retCode)) {
            // 这个判断条件别乱修改，请参考威富通的文档
            boolean ret = "SUCCESS".equals(retCode);
            // 支付成功
            if (ret) {
                mProgressDialog.show();
                new Thread() {
                    @Override public void run() {
                        super.run();
                        // 后台说等个5秒再请求订单信息，担心威富通没有回调成功
                        final Message msg = new Message();
                        msg.what = WFT_PAY_RESULT_LOAD;
                        msg.obj = YNetApiService.WFT_PAY_CALLBACK + "&transno=" + wftUniqueTransno;
                        UIHandler.sendMessageDelayed(msg, 5000);
                    }
                }.start();
            }
            // 支付失败
            else {
                // 告诉用户支付失败了，什么都不做
                toast2User("支付失败，请重试.");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 页面用到的 WebView
     */
    private class GameView extends WebView {

        public GameView(Context context) {
            super(context);
            getSettings().setUseWideViewPort(true);
            getSettings().setLoadWithOverviewMode(true);
            getSettings().setJavaScriptEnabled(true);
            getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            getSettings().setSupportZoom(true);
            getSettings().setAppCacheEnabled(true);
            getSettings().setDatabaseEnabled(true);
            getSettings().setDomStorageEnabled(true);
            getSettings().setDefaultTextEncodingName("UTF-8");
        }

        @Override public void onPause() {
            super.onPause();
            Logger.i(">>> WebView onPause");
            this.stopLoading();
        }
    }

    /**
     * WebViewClient
     */
    private final WebViewClient mWebViewClient = new WebViewClient() {
        // url拦截操作
        @Override public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            return super.shouldInterceptRequest(view, url);
        }

        @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 登录拦截
            if (signInUpInterceptor(url)) {
                UIHandler.sendEmptyMessage(PlayGameActivity.TOKEN_OVERDUE);
                return true;
            }
            // 支付拦截
            if (payCheckInterceptor(url)) {
                final Message msg = new Message();
                msg.what = PlayGameActivity.INIT_PAYMENT;
                msg.obj = url;
                UIHandler.sendMessage(msg);
                return true;
            }
            view.loadUrl(url);
            return true;
        }

        // 加载中操作
        @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        private boolean alipayInterceptor(String url) {
            return url.contains("pay_type=9");
        }

        private boolean WXPayInterceptor(String url) {
            return url.contains("pay_type=39") || url.contains("pay_type=59");
        }

        // 加载后操作
        @Override public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // 支付结果拦截
            if (payResultInterceptor(url)) {
                view.clearHistory();
            }
        }

        private boolean signInUpInterceptor(String url) {
            final String signUpURL =
                "http://h5.7676.com/index.php?m=member&c=index&a=login&type=app";
            return url.equals(signUpURL);
        }

        private boolean payCheckInterceptor(String url) {
            //final String payTag1 = "http://h5.7676.com/index.php?m=index&c=PayH5&a=transaction";
            //final String payTag2 = "http://h5.7676.com/api/transaction";
            final String payTag3 = "http://h5.7676.com/api/pay/save";
            return url.contains(payTag3);
        }

        private boolean payResultInterceptor(String url) {
            final String payCallback = YNetApiService.WFT_PAY_CALLBACK;
            return url.contains(payCallback);
        }

        // url重载操作
        //@TargetApi(21) @Override public boolean shouldOverrideUrlLoading(WebView view,
        //    WebResourceRequest request) {
        //    // 使用当前的webView来加载目标url
        //    view.loadUrl(request.getUrl().toString());
        //    return true;
        //}

        //@Override public boolean shouldOverrideUrlLoading(WebView view, String targetURL) {
        //    // 使用当前的webView来加载目标url
        //    view.loadUrl(targetURL);
        //    return true;
        //}

        //private void jsGetElementById(WebView view, String domId) {
        //    view.loadUrl("javascript:window.HTML_HANDLER.handleShareData("
        //        + "document.getElementById('"
        //        + domId
        //        + "').innerHTML"
        //        + ");");
        //}
    };

    /**
     * Dialog 监听
     */

    private DialogInterface.OnClickListener confirmListener = (dialog, which) -> {
        // -1 确认, -2 取消
        if (which == -1) {
            switch (dialogWhat) {
                case TOKEN_OVERDUE:
                    this.startActivityForResult(LoginActivity.buildIntent(this, null),
                        TOKEN_OVERDUE);
                    this.finish();
                    break;
                default:
                case FORCE_TO_EXIT:
                case GAME_LOAD_EXCEPTION:
                    finish();
                    break;
            }
        } else if (which == -2) {
            switch (dialogWhat) {
                case FORCE_TO_EXIT:
                    dialog.dismiss();
                    break;
                default:
                case TOKEN_OVERDUE:
                case GAME_LOAD_EXCEPTION:
                    finish();
                    break;
            }
        } else {
            Logger.e(">>> GO FUCK YOURSELF!!");
            finish();
        }
    };

    /**
     * JavaScriptShareDataHandler
     */
    //private class JavaScriptShareDataHandler {
    //    @JavascriptInterface public void handleShareData(String jsonData) {
    //        if (jsonData == null) {
    //            Logger.i(">>> CAN NOT CATCH ANYTHING FROM VIEW.");
    //            return;
    //        }
    //        Logger.i(">>> Shared data is: " + jsonData);
    //        switch (handleMassage(jsonData)) {
    //            case TOKEN_OVERDUE:
    //                tokenOverdueHandler();
    //                break;
    //            default:
    //            case GAME_LOAD_EXCEPTION:
    //                break;
    //        }
    //    }
    //
    //    private int handleMassage(String jsonData) {
    //        JSONObject jsonObject = JSONObject.parseObject(jsonData);
    //        int retIs;
    //        switch (jsonObject.get("ret").toString()) {
    //            case "-1":
    //                retIs = TOKEN_OVERDUE;
    //                break;
    //            default:
    //                retIs = GAME_LOAD_EXCEPTION;
    //                break;
    //        }
    //        return retIs;
    //    }
    //
    //    private void tokenOverdueHandler() {
    //        UIHandler.sendEmptyMessage(PlayGameActivity.TOKEN_OVERDUE);
    //    }
    //}
}