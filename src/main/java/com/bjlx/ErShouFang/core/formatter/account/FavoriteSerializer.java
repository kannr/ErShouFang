package com.bjlx.ErShouFang.core.formatter.account;

import com.bjlx.ErShouFang.model.account.Favorite;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 收藏序列化
 */
public class FavoriteSerializer extends JsonSerializer<Favorite> {

    @Override
    public void serialize(Favorite favorite, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Favorite.fd_id, favorite.getId() == null ? "" : favorite.getId().toString());
            gen.writeStringField(Favorite.fd_oauthId, favorite.getOauthId() == null ? "" : favorite.getOauthId());
            gen.writeNumberField(Favorite.fd_favoriteType, favorite.getFavoriteType() == null ? 0 : favorite.getFavoriteType());
            gen.writeStringField(Favorite.fd_itemId, favorite.getItemId() == null ? "" : favorite.getItemId().toString());

            gen.writeStringField(Favorite.fd_title, favorite.getTitle() == null ? "" : favorite.getTitle());
            gen.writeNumberField(Favorite.fd_favoriteTime, favorite.getFavoriteTime() == null ? 0L : favorite.getFavoriteTime());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}