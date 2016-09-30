package com.i7676.qyclient.functions.h5game;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.i7676.qyclient.util.DialogUtils;

public class PlayGameActivity extends AppCompatActivity {

    public static final String GAME_URL = "game_url";

    public static Intent buildIntent(Context ctx, Bundle args) {
        final Intent mIntent = new Intent(ctx, PlayGameActivity.class);
        if (args != null) mIntent.putExtras(args);
        return mIntent;
    }

    private String url;
    private GameView mGameView;

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
        mGameView.getSettings().setDefaultTextEncodingName("UTF-8");
        mGameView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
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

    void exitConfirm() {
        DialogUtils.showAlert(this, "提示", "退出游戏并返回主页面？", "确认", confirmListener, "取消",
            confirmListener);
    }

    private DialogInterface.OnClickListener confirmListener = (dialog, which) -> {
        switch (which) {
            case -1: // 确认
                finish();
                break;
            default:
            case -2: // 取消
                dialog.dismiss();
                break;
        }
    };
}