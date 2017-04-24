package com.bjlx.ErShouFang.core.formatter.misc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.bjlx.ErShouFang.model.misc.ValidationCode;
import java.io.IOException;

/**
 * 验证码序列化
 * Created by pengyt on 2016/10/23.
 */
public class ValidationCodeSerializer extends JsonSerializer<ValidationCode> {

    @Override
    public void serialize(ValidationCode validationCode, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            
            gen.writeStringField("id", validationCode.getId() == null ? "" : validationCode.getId().toString());
            gen.writeStringField("tel", validationCode.getTel() == null ? "" : validationCode.getTel());

            gen.writeStringField("code", validationCode.getCode() == null ? "" : validationCode.getCode());
            gen.writeNumberField("action", validationCode.getAction() == null ? -1 : validationCode.getAction());
            gen.writeNumberField("failCnt", validationCode.getFailCnt() == null ? 0 : validationCode.getFailCnt());
            gen.writeNumberField("createTime", validationCode.getCreateTime() == null ? 0 : validationCode.getCreateTime());
            gen.writeNumberField("expireTime", validationCode.getExpireTime() == null ? 0 : validationCode.getExpireTime());
            gen.writeNumberField("lastSendTime", validationCode.getLastSendTime() == null ? 0 : validationCode.getLastSendTime());
            gen.writeNumberField("resendTime", validationCode.getResendTime() == null ? 0 : validationCode.getResendTime());
            
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
