package com.bjlx.ErShouFang.core.formatter.misc;

import com.bjlx.ErShouFang.model.misc.Position;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PositionSerializer extends JsonSerializer<Position> {

    @Override
    public void serialize(Position position, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            if(position.getName() != null)
            	gen.writeStringField(Position.fd_name, position.getName());
            gen.writeNumberField(Position.fd_lat, position.getLat() == null ? 39.15 : position.getLat());
            gen.writeNumberField(Position.fd_lng, position.getLng() == null ? 116.07: position.getLng());
            if(position.getDesc() != null)
            	gen.writeStringField(Position.fd_desc, position.getDesc());
        	
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
