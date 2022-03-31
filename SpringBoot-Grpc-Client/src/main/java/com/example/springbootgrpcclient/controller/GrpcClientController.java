package com.example.springbootgrpcclient.controller;

import com.alibaba.fastjson.JSON;
import com.dashuai.learning.grpc.lib.proto.UserOuterClass;
import com.example.springbootgrpcclient.service.UserClientService;

import com.google.protobuf.InvalidProtocolBufferException;
import com.wsb.util.ProtobufUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Scanner;

@RestController
public class GrpcClientController {

    private static final Logger log = LoggerFactory.getLogger(GrpcClientController.class);
    @Autowired
    private UserClientService userClientService;/**/

//    @RequestMapping("/greeter/{name}")
//    public String printMessage(@PathVariable String name) {
//    }
    @RequestMapping("/test")
    public String test() {
        log.info("dispatcher成功");
        return "hello grpc";
    }
    @RequestMapping(value="/user",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public boolean saveUser(HttpServletRequest request) throws IOException {
        Scanner scan = new Scanner(request.getInputStream());
        StringBuilder strb = new StringBuilder();
        while (scan.hasNext()) {
            strb.append(scan.next());
        }
        //log.info(strb.toString());
        UserOuterClass.UserData userData = ProtobufUtils.jsonToPf(strb.toString(), UserOuterClass.UserData.newBuilder());
        log.info(userData.toString());
        return userClientService.saveUser(userData);
    }

    @RequestMapping("/user/{id}")
    public String getUser(@PathVariable int id) throws InvalidProtocolBufferException {
        UserOuterClass.UserData userData = userClientService.getUser(id);
        log.info("fastjson解析string：" + JSON.toJSONString(userData));
        log.info("grpc自带util解析：" + ProtobufUtils.pfToJson(userData));
        return JSON.toJSONString(userData.getName());
    }


}
