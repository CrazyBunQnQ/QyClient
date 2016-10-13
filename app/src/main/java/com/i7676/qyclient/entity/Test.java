package com.i7676.qyclient.entity;

/**
 * Created by Administrator on 2016/10/11.
 */

public class Test {

    Detail detail;
    Gift gift;

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public class Detail{


        /**
         * catname : 乱斗封神
         * catdir : egret
         * gameId : 90276
         * bid : 50
         * introduce : 使用说明：游戏右上角——更多——设置——礼包兑换——输入兑换码 礼包内容：兵粮丸*3、铜币*5W、橙色魂匣宝箱*1
         * gid : 253
         * add_time : 1473670417
         * icon : http://h5.7676.com/statics/images/gift_icon/50.png
         * remain : 93
         * consume : 7
         */

        private String catname;
        private String catdir;
        private int gameId;
        private String bid;
        private String introduce;
        private int gid;
        private int add_time;
        private String icon;
        private int remain;
        private int consume;

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }

        public String getCatdir() {
            return catdir;
        }

        public void setCatdir(String catdir) {
            this.catdir = catdir;
        }

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getRemain() {
            return remain;
        }

        public void setRemain(int remain) {
            this.remain = remain;
        }

        public int getConsume() {
            return consume;
        }

        public void setConsume(int consume) {
            this.consume = consume;
        }
    }
    public class Gift{

    }
}
