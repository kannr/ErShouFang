package com.bjlx.ErShouFang.core.formatter.account;

import com.bjlx.ErShouFang.model.account.Favorite;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class FavoriteFormatter {

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Favorite.class, new FavoriteSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}
