package com.bjlx.ErShouFang.controller;

import com.bjlx.ErShouFang.core.AccountAPI;
import com.bjlx.ErShouFang.requestmodel.ContactReq;
import com.bjlx.ErShouFang.requestmodel.OAuthUserInfoReq;
import com.bjlx.ErShouFang.requestmodel.ValidationCodeReq;
import com.bjlx.ErShouFang.utils.CommonUtil;
import com.bjlx.ErShouFang.utils.Constant;
import com.bjlx.ErShouFang.utils.ErShouFangResult;
import com.bjlx.ErShouFang.utils.ErrorCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户账户相关
 * Created by pengyt on 2016/10/20.
 */
@Controller
public class AccountController {

    @RequestMapping(value = "/app/test", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String test() {

        return "{\"code\":200,\"msg\":\"成功\"}";
    }

    /**
     * 发送验证码, 接口编码1001
     * @param validationCodeReq 参数
     * @return 用户接收验证码的手机号，验证码
     */
    @RequestMapping(value = "/app/validationcodes", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String sendValidationCode(@RequestBody ValidationCodeReq validationCodeReq) {
        // 参数校验
        // account必须存在
        if(validationCodeReq.getTel() == null) {
            return ErShouFangResult.getResult(ErrorCode.TEL_NULL_1001);
        }
        // action必须存在
        if(validationCodeReq.getAction() == null) {
            return ErShouFangResult.getResult(ErrorCode.ACTION_NULL_1001);
        }

        // 检查action的值的合法性
        if(Constant.BIND_TEL_ACTION != validationCodeReq.getAction()) {
            return ErShouFangResult.getResult(ErrorCode.ACTION_LIMIT_1001);
        }

        // 校验是否过了允许下次发送发验证码的时间
        try {
            if(AccountAPI.isAllowSendValidationCode(validationCodeReq.getTel())) {
                /**
                 * 发送短信验证码, isTel参数取值
                 * true表示使用的是手机号
                 * false表示使用的是邮箱
                 */
                return AccountAPI.sendValidationCode(validationCodeReq.getTel(), validationCodeReq.getAction());
            } else {
                return ErShouFangResult.getResult(ErrorCode.TIME_LIMIT_1001);
            }
        } catch(Exception e1) {
            return ErShouFangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 保存用户联系信息, 接口编码1002
     * @param contactReq 联系信息
     * @return 合法的令牌
     */
    @RequestMapping(value = "/app/contacts", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String checkValidationCode(@RequestBody ContactReq contactReq) {

        // 参数校验
        // tel必须存在
        if(contactReq.getTel() == null) {
            return ErShouFangResult.getResult(ErrorCode.TEL_NULL_1002);
        }
        // code必须存在
        if(contactReq.getCode() == null) {
            return ErShouFangResult.getResult(ErrorCode.CODE_NULL_1002);
        }

        // 检查验证码是否为六位数字
        if(!AccountAPI.isCode(contactReq.getCode())) {
            return ErShouFangResult.getResult(ErrorCode.CODE_INVALID_1002);
        }

        if(contactReq.getWeixin() == null) {
            return ErShouFangResult.getResult(ErrorCode.WEIXIN_NULL_1002);
        }

        if(contactReq.getName() == null) {
            return ErShouFangResult.getResult(ErrorCode.NAME_NULL_1002);
        }

        // account必须手机号或者邮箱号
        if(CommonUtil.isTelLegal(contactReq.getTel())) {
            try {
                if(AccountAPI.checkValidationCode(contactReq.getTel(), contactReq.getCode())) {
                    // 保存用户信息
                    return AccountAPI.addContact(contactReq.getName(), contactReq.getWeixin(), contactReq.getTel());
                } else {
                    return ErShouFangResult.getResult(ErrorCode.CODE_INVALID_1002);
                }
            } catch(Exception e1) {
                return ErShouFangResult.getResult(ErrorCode.SERVER_EXCEPTION);
            }
        } else {
            return ErShouFangResult.getResult(ErrorCode.TEL_FORMAT_1002);
        }

    }

    /**
     * 第三方登录, 接口编码1003
     * @param oauthUserInfoReq 用户第三方信息
     * @return 用户信息
     */
    @RequestMapping(value = "/app/oauthlogin", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String oauthLogin(@RequestBody OAuthUserInfoReq oauthUserInfoReq) {

        if(oauthUserInfoReq.getProvider() == null) {
            return ErShouFangResult.getResult(ErrorCode.PROVIDER_NULL_1003);
        }
        if(oauthUserInfoReq.getOauthId() == null) {
            return ErShouFangResult.getResult(ErrorCode.OAUTHID_NULL_1003);
        }
        if(oauthUserInfoReq.getToken() == null) {
            return ErShouFangResult.getResult(ErrorCode.TOKEN_NULL_1003);
        }

        try {
            return AccountAPI.oauthlogin(oauthUserInfoReq.getProvider(), oauthUserInfoReq.getOauthId(), oauthUserInfoReq.getNickName(), oauthUserInfoReq.getAvatar(), oauthUserInfoReq.getToken());
        } catch (Exception e) {
            return ErShouFangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 退出登录, 接口编码1004
     * @param oauthId 用户id
     * @param key 不羁旅行令牌
     * @return 结果信息
     */
    @RequestMapping(value = "/app/logout", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public @ResponseBody String logout(@RequestHeader("oauthId") String oauthId, @RequestHeader("key") String key) {

        try {
            return AccountAPI.logout(oauthId, key);
        } catch (Exception e) {
            e.printStackTrace();
            return ErShouFangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
