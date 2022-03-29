package com.example.springbootgrpclib.util;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class ProtobufUtils {
    /**
     * json数据转换为pb对象
     *
     * @param <T>     the type parameter
     * @param json    the json
     * @param builder the builder
     * @return the t
     * @throws InvalidProtocolBufferException the invalid protocol buffer exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToPf(String json, Message.Builder builder) throws InvalidProtocolBufferException {
        if (builder == null) {
            return null;
        }
        JsonFormat.parser().ignoringUnknownFields().merge(json, builder);
        return (T) builder.build();
    }

    /**
     * json数据转换为pb对象
     *
     * @param <T>     the type parameter
     * @param entity  the entity
     * @param builder the builder
     * @return the t
     * @throws InvalidProtocolBufferException the invalid protocol buffer exception
     */
    public static <T> T jsonToPf(Object entity, Message.Builder builder) throws InvalidProtocolBufferException {
        if (builder == null || entity == null) {
            return null;
        }
        return jsonToPf(JSON.toJSONString(entity), builder);
    }

    /**
     * Pf to json string.
     *
     * @param message the message
     * @return the string
     * @throws InvalidProtocolBufferException the invalid protocol buffer exception
     */
    public static String pfToJson(Message message) throws InvalidProtocolBufferException {
        return JsonFormat.printer().print(message);
    }
}
