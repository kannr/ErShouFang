package com.bjlx.ErShouFang.core.formatter.account;


import com.bjlx.ErShouFang.model.account.UserInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 用户信息
 * Created by pengyt on 2016/10/28.
 */
public class UserInfoSerializer extends JsonSerializer<UserInfo> {

    @Override
    public void serialize(UserInfo userInfo, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();

            gen.writeStringField(UserInfo.fd_id, userInfo.getId() == null ? "" : userInfo.getId().toString());

            gen.writeStringField(UserInfo.fd_provider, userInfo.getProvider() == null ? "" : userInfo.getProvider());

            gen.writeStringField(UserInfo.fd_oauthId, userInfo.getOauthId() == null ? "" : userInfo.getOauthId());

            gen.writeStringField(UserInfo.fd_nickName, userInfo.getNickName() == null ? "" : userInfo.getNickName());

            gen.writeStringField(UserInfo.fd_avatar, userInfo.getAvatar() == null ? "" : userInfo.getAvatar());

            gen.writeNumberField(UserInfo.fd_createTime, userInfo.getCreateTime() == null ? 0L : userInfo.getCreateTime());

            if(userInfo.getLoginTime() != null)
                gen.writeNumberField(UserInfo.fd_loginTime, userInfo.getLoginTime());

            if(userInfo.getNumber() != null)
                gen.writeStringField(UserInfo.fd_number, userInfo.getNumber());

            if(userInfo.getRealName() != null)
                gen.writeStringField(UserInfo.fd_realName, userInfo.getRealName());

            if(userInfo.getTel() != null)
                gen.writeStringField(UserInfo.fd_tel, userInfo.getTel());

            if(userInfo.getWeixin() != null)
                gen.writeStringField(UserInfo.fd_weixin, userInfo.getWeixin());

            if(userInfo.getKey() != null)
            	gen.writeStringField(UserInfo.fd_key, userInfo.getKey());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
