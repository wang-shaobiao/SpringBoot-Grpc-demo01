package com.example.service;

import com.dashuai.learning.grpc.lib.proto.UserGrpc;
import com.dashuai.learning.grpc.lib.proto.UserOuterClass;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wsb.util.ProtobufUtils;

import java.util.HashMap;
import java.util.Map;

@GrpcService
public class UserService extends UserGrpc.UserImplBase {
    private final static Logger log = LoggerFactory.getLogger(UserService.class);
    private Map<Integer, UserOuterClass.UserData> map = new HashMap<>();

    @Override
    public void saveUser(UserOuterClass.UserData request, StreamObserver<UserOuterClass.UserResponse> responseObserver) {
        try {
            map.put(request.getId(), request);
        } catch (Exception e) {
            log.error("保存失败了！msg:{}",e.getMessage());
        }
        UserOuterClass.UserResponse.Builder builder = UserOuterClass.UserResponse.newBuilder().setIsSuccess(true);
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
    @Override
    public void getUser (UserOuterClass.UserRequest request, StreamObserver<UserOuterClass.UserData> responseObserver) {
        UserOuterClass.UserData ud = map.get(request.getId()) ;
        log.info("stub找到");
        if (ud == null) {
            String temp = "{\"id\":0,\"name\":\"默认\",\"sex\":\"男\",\"age\":20,\"remark\":\"路人甲\"}";
            try {
                ud = ProtobufUtils.jsonToPf(temp, UserOuterClass.UserData.newBuilder());
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }

            map.put(request.getId(),ud);
        }
        responseObserver.onNext(ud);
        responseObserver.onCompleted();
    }
}
