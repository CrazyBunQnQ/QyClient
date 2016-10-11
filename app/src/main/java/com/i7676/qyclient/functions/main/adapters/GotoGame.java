package com.i7676.qyclient.functions.main.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.functions.h5game.PlayGameActivity;
import com.i7676.qyclient.functions.main.MainActivity;

class GotoGame implements View.OnClickListener {

    private String gameUrl;
    private Context mContext;

    GotoGame(Context mContext, String gameUrl) {
        this.gameUrl = gameUrl;
        this.mContext = mContext;
    }

    @Override public void onClick(View v) {
        if (QyClient.curUser == null) {
            if (mContext instanceof MainActivity) {
                ((MainActivity) mContext).showLogin();
            } else {
                Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        Bundle args = new Bundle();
        args.putString(PlayGameActivity.GAME_URL,
            gameUrl + "&token=" + QyClient.curUser.getToken());
        mContext.startActivity(PlayGameActivity.buildIntent(mContext, args));
    }
}