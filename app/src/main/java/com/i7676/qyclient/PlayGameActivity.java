package com.i7676.qyclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.roughike.bottombar.BottomBar;

/**
 * Created by Administrator on 2016/9/12.
 */
public class PlayGameActivity extends BaseActivity {

  public static final String GAME_URL = "game_url";

  public static Intent buildIntent(Context ctx, Bundle args) {
    final Intent mIntent = new Intent(ctx, PlayGameActivity.class);
    if (args != null) mIntent.putExtras(args);
    return mIntent;
  }

  private String url;
  private GameView mGameView;

  @Override public BottomBar getBottomBar() {
    return null;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent data = getIntent();
    url = data.getStringExtra(GAME_URL);

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
    mGameView.getSettings().setUseWideViewPort(true);
    mGameView.getSettings().setLoadWithOverviewMode(true);
    mGameView.getSettings().setJavaScriptEnabled(true);
    mGameView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    mGameView.getSettings().setSupportZoom(true);
    mGameView.getSettings().setAppCacheEnabled(true);
    mGameView.getSettings().setDatabaseEnabled(true);
    mGameView.getSettings().setDomStorageEnabled(true);
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
    }
  }

  //设置回退
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if ((keyCode == KeyEvent.KEYCODE_BACK) && mGameView.canGoBack()) {
      mGameView.goBack();
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }
}
