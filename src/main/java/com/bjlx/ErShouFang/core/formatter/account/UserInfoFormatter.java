package com.bjlx.ErShouFang.core.formatter.account;

import com.bjlx.ErShouFang.model.account.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by pengyt on 2016/10/28.
 */
public class UserInfoFormatter {
    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(UserInfo.class, new com.bjlx.ErShouFang.core.formatter.account.UserInfoSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
