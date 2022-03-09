package me.hgj.jetpackmvvm.network;

/**
 * Author:jianbo
 * <p>
 * Create Time:2019/4/16 10:25
 * <p>
 * Email:1245092675@qq.com
 * <p>
 * Describe:所有的请求Bean包装类（数据实体类现在只需要构建json内BODY对象内的数据）
 * json例如
 * eg1:{"HEAD":{"SIGN":"0841B9112C96"},
 * "BODY":{"inVersion":1,"sysType":3,"userId":"27","timestamp":"20180510163349"}}
 * eg2:{"HEAD":{"TOKEN":"0841B9112C96"},
 * "BODY":{"inVersion":1,"sysType":3,"userId":"27","timestamp":"20180510163349"}}
 */

public class BaseHttpResultBean<T> {

    /**
     * HEAD : {"MSG":"操作成功","CODE":"000"}
     * BODY : {"code":1934}
     */

    private HEADBean HEAD;
    private T BODY;

    public HEADBean getHEAD() {
        return HEAD;
    }

    public void setHEAD(HEADBean HEAD) {
        this.HEAD = HEAD;
    }

    public T getBODY() {
        return BODY;
    }

    public void setBODY(T BODY) {
        this.BODY = BODY;
    }

    public static class HEADBean {
        /**
         * MSG : 操作成功
         * CODE : 000
         */

        private String MSG;
        private String CODE;

        public String getMSG() {
            return MSG;
        }

        public void setMSG(String MSG) {
            this.MSG = MSG;
        }

        public String getCODE() {
            return CODE;
        }

        public void setCODE(String CODE) {
            this.CODE = CODE;
        }
    }

}
