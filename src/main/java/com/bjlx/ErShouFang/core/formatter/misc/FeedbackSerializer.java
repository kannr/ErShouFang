package com.bjlx.ErShouFang.core.formatter.misc;

import com.bjlx.ErShouFang.model.misc.Feedback;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class FeedbackSerializer extends JsonSerializer<Feedback> {

    @Override
    public void serialize(Feedback feedback, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Feedback.fd_id, feedback.getId() == null ? "" : feedback.getId().toString());
            gen.writeStringField(Feedback.fd_oauthId, feedback.getOauthId() == null ? "" : feedback.getOauthId());
            gen.writeStringField(Feedback.fd_content, feedback.getContent() == null ? "" : feedback.getContent());
            gen.writeNumberField(Feedback.fd_time, feedback.getTime() == null ? 0L : feedback.getTime());
            if(feedback.getOrigin() != null)
            	gen.writeStringField(Feedback.fd_origin, feedback.getOrigin());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

