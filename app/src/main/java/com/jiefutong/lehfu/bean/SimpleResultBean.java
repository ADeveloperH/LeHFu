package com.jiefutong.lehfu.bean;

/**
 * author：hj
 * time: 2018/1/20 0020 15:10
 * description:
 */


public class SimpleResultBean {

    /**
     * status : 1
     * info : 短信发送成功
     */

    private int status;
    private String info;
    private String app_token = "-1";

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token;
    }
}
