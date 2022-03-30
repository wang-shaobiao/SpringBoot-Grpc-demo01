package com.wsb.util;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class ProtobufUtils {
    /**
     * json-string格式数据转换为Protobuf对象
     * @param json
     * @param builder
     * @param <T>
     * @return
     * @throws InvalidProtocolBufferException
     */
    public static <T> T jsonToPf(String json, Message.Builder builder) throws InvalidProtocolBufferException {
        if (builder == null) {
            return null;
        }
        JsonFormat.parser().ignoringUnknownFields().merge(json, builder);
        return (T) builder.build();
    }

    /**
     * 实体对象转换为protobuf对象
     * @param entity
     * @param builder
     * @param <T>
     * @return
     * @throws InvalidProtocolBufferException
     */
    public static <T> T jsonToPf(Object entity, Message.Builder builder) throws InvalidProtocolBufferException {
        if (builder == null || entity == null) {
            return null;
        }
        return jsonToPf(JSON.toJSONString(entity), builder);
    }

    /**
     * Pf to json String
     * @param message
     * @return
     * @throws InvalidProtocolBufferException
     */
    public static String pfToJson(Message message) throws InvalidProtocolBufferException {
        return JsonFormat.printer().print(message);
    }
}
