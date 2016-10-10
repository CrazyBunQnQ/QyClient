package com.i7676.qyclient.functions.h5game;

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
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.functions.h5game.pay.PaymentCheckActivity;
import com.i7676.qyclient.functions.login.LoginActivity;
import com.i7676.qyclient.util.DialogUtils;
import com.orhanobut.logger.Logger;

public class PlayGameActivity extends AppCompatActivity {

    public static final String GAME_URL = "game_url";

    public static Intent buildIntent(Context ctx, Bundle args) {
        final Intent mIntent = new Intent(ctx, PlayGameActivity.class);
        if (args != null) mIntent.putExtras(args);
        return mIntent;
    }

    private String url;
    private GameView mGameView;
    private final WebViewClient mWebViewClient = new WebViewClient() {
        // url拦截操作
        @Override public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            return super.shouldInterceptRequest(view, url);
        }

        // 加载中操作
        @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // 登录拦截
            if (signInUpInterceptor(url)) {
                UIHandler.sendEmptyMessage(PlayGameActivity.TOKEN_OVERDUE);
                view.onPause();
                return;
            }
            // 支付拦截
            if (payCheckInterceptor(url)) {
                final Message msg = new Message();
                msg.what = PlayGameActivity.INIT_PAY_CHECK;
                msg.obj = url;
                UIHandler.sendMessage(msg);
                view.onPause();
                return;
            }
            super.onPageStarted(view, url, favicon);
        }

        // 加载后操作
        @Override public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
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

        // url重载操作
        //@TargetApi(21) @Override public boolean shouldOverrideUrlLoading(WebView view,
        //    WebResourceRequest request) {
        //    // 使用当前的webView来加载目标url
        //    view.loadUrl(request.getUrl().toString());
        //    return true;
        //}

        //@Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //    // 使用当前的webView来加载目标url
        //    view.loadUrl(url);
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

    public static final int TOKEN_OVERDUE = -1;
    public static final int GAME_LOAD_EXCEPTION = -3;
    public static final int FORCE_TO_EXIT = -2;
    public static final int INIT_PAY_CHECK = -4;
    private AlertDialog mAlertDialog;
    private int dialogWhat = FORCE_TO_EXIT;

    private final Handler UIHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TOKEN_OVERDUE:
                    showAlterDialog("登录已过期，请重新登录再进入游戏.", TOKEN_OVERDUE);
                    break;
                case INIT_PAY_CHECK:
                    final String payCheckUrl = (String) msg.obj;
                    final Bundle payParams = parseURL(payCheckUrl);
                    // 跳转支付方式界面
                    startActivity(
                        PaymentCheckActivity.buildIntent(PlayGameActivity.this, payParams));
                    break;
                default:
                case GAME_LOAD_EXCEPTION:
                    break;
            }
        }

        private Bundle parseURL(String url) {
            final Bundle payParams = new Bundle();
            payParams.putString("token", QyClient.curUser.getToken());
            payParams.putString("payType", "39");
            String paramsStr = url.split("[?]")[1];
            String params[] = paramsStr.split("[&]");
            for (String param : params) {
                String temp[] = param.split("[=]");
                if (temp.length == 2) {
                    payParams.putString(temp[0], temp[1]);
                }
            }
            return payParams;
        }
    };

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent data = getIntent();
        url = data.getStringExtra(GAME_URL);

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

        if (null == url || "".equals(url)) {
            url = "http://www.baidu.com";
        }
        mGameView.loadUrl(url);
    }

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

    private DialogInterface.OnClickListener confirmListener = (dialog, which) -> {
        // -1 确认, -2 取消
        if (which == -1) {
            switch (dialogWhat) {
                case TOKEN_OVERDUE:
                    this.startActivityForResult(LoginActivity.buildIntent(this, null),
                        TOKEN_OVERDUE);
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

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TOKEN_OVERDUE) {
            if (resultCode == RESULT_OK) {
                int pointIndex = url.indexOf("&token");

                if (pointIndex == -1) {
                    Logger.e(">>> TOKEN CAN NOT BE NULL！");
                    finish();
                    return;
                }

                url = url.substring(0, pointIndex);
                url += "&token=" + QyClient.curUser.getToken();
                mGameView.loadUrl(url);
            }
        } else {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}