package com.example.springbootgrpcclient.service;

import com.alibaba.fastjson.JSON;
import com.dashuai.learning.grpc.lib.proto.UserGrpc;
import com.dashuai.learning.grpc.lib.proto.UserOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserClientService {
    @GrpcClient("local-grpc-server")
    private UserGrpc.UserBlockingStub userStub;

    public boolean saveUser(UserOuterClass.UserData userData) {
//        UserGrpc.UserBlockingStub userBlockingStub = UserGrpc.newBlockingStub(serverChannel);
        System.out.println(JSON.toJSONString(userData));
        UserOuterClass.UserResponse userResponse = userStub.saveUser(userData);
        return userResponse.getIsSuccess();
    }

    public UserOuterClass.UserData getUser(int id) {
//        UserGrpc.UserBlockingStub userBlockingStub = UserGrpc.newBlockingStub(serverChannel);
        UserOuterClass.UserData userData = userStub.getUser(UserOuterClass.UserRequest.newBuilder().setId(id).build());
        System.out.println(JSON.toJSONString(userData));
        return userData;
    }
}
