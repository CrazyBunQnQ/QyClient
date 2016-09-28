package com.i7676.qyclient.util;

/**
 * Created by Administrator on 2016/9/13.
 */
public class ValueMapper {

    public static final String parsePayType(int payType) {
        String payTypeText = null;
        switch (payType) {
            default:
            case 1:
                payTypeText = "免费游戏";
                break;
            case 2:
                payTypeText = "付费游戏";
                break;
        }
        return payTypeText;
    }

    public static final String parseIsWebGame(int gameType) {
        String gameTypeText = null;
        switch (gameType) {
            default:
            case 1:
                gameTypeText = "网游";
                break;
            case 2:
                gameTypeText = "小游戏";
                break;
        }
        return gameTypeText;
    }
}
