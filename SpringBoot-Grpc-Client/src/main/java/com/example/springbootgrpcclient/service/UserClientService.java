package com.example.springbootgrpcclient.service;

import com.alibaba.fastjson.JSON;
import com.dashuai.learning.grpc.lib.proto.UserGrpc;
import com.dashuai.learning.grpc.lib.proto.UserGrpc.UserBlockingStub;
import com.dashuai.learning.grpc.lib.proto.UserOuterClass;
import io.grpc.Channel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserClientService {
    private static final Logger log = LoggerFactory.getLogger(UserClientService.class);

    @GrpcClient("local-grpc-server")
    //private Channel serverChannel;
    private UserGrpc.UserBlockingStub userStub ;

    public boolean saveUser(UserOuterClass.UserData userData) {
        //UserGrpc.UserBlockingStub userStub = UserGrpc.newBlockingStub(serverChannel);
        //System.out.println(JSON.toJSONString(userData));
        log.info(JSON.toJSONString(userData));
        UserOuterClass.UserResponse userResponse = userStub.saveUser(userData);
        return userResponse.getIsSuccess();
    }

    public UserOuterClass.UserData getUser(int id) {
        //UserGrpc.UserBlockingStub userStub = UserGrpc.newBlockingStub(serverChannel);
        UserOuterClass.UserData userData = userStub.getUser(UserOuterClass.UserRequest.newBuilder().setId(id).build());
        System.out.println(JSON.toJSONString(userData));
        return userData;
    }
}
