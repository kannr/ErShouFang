package com.bjlx.ErShouFang.requestmodel;

/**
 * 接收验证码接口相关参数
 * 所有字段定义成Object对象类型
 *
 * Created by xiaozhi on 2016/10/21.
 */
public class ValidationCodeReq {

    /**
     * 手机号
     */
    private String tel;

    /**
     * 验证码的用途
     * 1表示新用户注册，2表示重置密码，3表示绑定手机号，4表示绑定邮箱
     */
    private Integer action;

    /**
     * 验证码
     */
    private String code;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
