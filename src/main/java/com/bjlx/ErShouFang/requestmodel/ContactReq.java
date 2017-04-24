package com.bjlx.ErShouFang.requestmodel;

/**
 * 联系信息
 * Created by pengyt on 2017/4/24.
 */
public class ContactReq {

    private String name;

    private String tel;

    private String weixin;

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
