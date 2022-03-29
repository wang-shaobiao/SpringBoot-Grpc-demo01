package com.example.springbootgrpcclient.service;

import com.alibaba.fastjson.JSON;
import com.dashuai.learning.grpc.lib.proto.UserGrpc;
import com.dashuai.learning.grpc.lib.proto.UserOuterClass;
import io.grpc.Channel;
import org.springframework.stereotype.Service;

@Service
public class UserClientService {
    private Channel serverChannel;

    public boolean saveUser(UserOuterClass.UserData userData) {
        UserGrpc.UserBlockingStub userBlockingStub = UserGrpc.newBlockingStub(serverChannel);
        System.out.println(JSON.toJSONString(userData));
        UserOuterClass.UserResponse userResponse = userBlockingStub.saveUser(userData);
        return userResponse.getIsSuccess();
    }

    public UserOuterClass.UserData getUser(int id) {
        UserGrpc.UserBlockingStub userBlockingStub = UserGrpc.newBlockingStub(serverChannel);
        UserOuterClass.UserData userData = userBlockingStub.getUser(UserOuterClass.UserRequest.newBuilder().setId(id).build());
        System.out.println(JSON.toJSONString(userData));
        return userData;
    }
}
