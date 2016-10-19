package com.i7676.qyclient.im;

import android.os.Bundle;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

public class RCIMReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {
    /**
     * 收到消息的处理。
     *
     * @param message 收到的消息实体。
     * @param left 剩余未拉取消息数目。
     * @return 收到消息是否处理完成，true 表示走自已的处理方式，false 走融云默认处理方式。
     */
    @Override public boolean onReceived(Message message, int left) {
        TextMessage txtContent = (TextMessage) message.getContent();
        String content = txtContent.getContent();
        String extra = txtContent.getExtra();

        // 根据建学的提示，extra是个json串
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(extra);
        } catch (Exception e) {
            Logger.e(">>> json解析错误");
        }
        if (jsonObject == null) {
            return false;
        }
        /**
         * 焦建学 2016/10/19 17:57:35
         * h5好像还没有做
         * 焦建学 2016/10/19 17:57:44
         * 根据extra里的信息，做相应的展示
         * 焦建学 2016/10/19 17:58:37
         * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> type表示类型  theme就是动态，action是动作表示评论，id是动态的ID
         * 焦建学 2016/10/19 17:59:33
         * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 然后根据动态的ID去服务器请求,就可以动态的展示这条消息了
         * 焦建学 2016/10/19 18:01:18
         * 所以 处理的消息的逻辑，还得判断type值，根据type值做不同的处理
         */
        String type = (String) jsonObject.get("type");
        String action = (String) jsonObject.get("action");
        String id = (String) jsonObject.get("id");

        // 根据 type 判断是什么动作
        switch (type) {
            case IMConstants.TYPE_THEME:
                final Bundle args = new Bundle();
                args.putString("content", content);
                args.putString("action", action);
                args.putString("id", id);
                //......
                dealWithTheme(args);
                break;
        }
        return true;
    }

    private void dealWithTheme(Bundle args) {
        // 启动相应的 Activity
    }
}