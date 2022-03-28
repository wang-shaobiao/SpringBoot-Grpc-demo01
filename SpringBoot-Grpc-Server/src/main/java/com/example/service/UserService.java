package com.example.service;

import com.dashuai.learning.grpc.lib.proto.UserGrpc;
import com.dashuai.learning.grpc.lib.proto.UserOuterClass;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
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

    public void getUser(UserOuterClass.UserData request, StreamObserver<UserOuterClass.UserData> responseObserver) {
        responseObserver.onNext(map.get(request.getId()));
        responseObserver.onCompleted();
    }
}
