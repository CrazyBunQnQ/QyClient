package com.i7676.qyclient.functions.main.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
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
            ((MainActivity) mContext).showLogin();
            return;
        }

        Bundle args = new Bundle();
        args.putString(PlayGameActivity.GAME_URL,
            gameUrl + "&token=" + QyClient.curUser.getToken());
        mContext.startActivity(PlayGameActivity.buildIntent(mContext, args));
    }
}