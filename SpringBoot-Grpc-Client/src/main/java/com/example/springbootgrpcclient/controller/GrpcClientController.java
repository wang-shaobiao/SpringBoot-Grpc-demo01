package com.example.springbootgrpcclient.controller;

import com.alibaba.fastjson.JSON;
import com.dashuai.learning.grpc.lib.proto.UserOuterClass;
import com.example.springbootgrpcclient.service.UserClientService;
import com.example.springbootgrpclib.util.ProtobufUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Scanner;

@RestController
public class GrpcClientController {

    @Autowired
    private UserClientService userClientService;/**/

//    @RequestMapping("/greeter/{name}")
//    public String printMessage(@PathVariable String name) {
//    }
    @RequestMapping("/test")
    public String test() {
        return "hello grpc";
    }
    @RequestMapping(value="/user",produces = "application/json;charset=UTF-8")
    public boolean saveUser(HttpServletRequest request) throws IOException {
        Scanner scan = new Scanner(request.getInputStream());
        StringBuilder strb = new StringBuilder();
        while (scan.hasNext()) {
            strb.append(scan.next());
        }
        System.out.println(strb);
        return userClientService.saveUser(ProtobufUtils.jsonToPf(strb, UserOuterClass.UserData.newBuilder()));
    }

    @RequestMapping("/user/{id}")
    public String getUser(@PathVariable int id) {
        UserOuterClass.UserData userData = userClientService.getUser(id);
        return JSON.toJSONString(userData);
    }


}
