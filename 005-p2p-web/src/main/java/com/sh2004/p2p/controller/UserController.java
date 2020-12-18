package com.sh2004.p2p.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sh2004.p2p.eneity.User;
import com.sh2004.p2p.result.Result;
import com.sh2004.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/17 16:43
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class UserController {
    @Reference(interfaceClass = UserService.class,timeout = 20000,version = "1.0.0")
    private UserService userService;


    @RequestMapping("/login")
    public String login(User user, HttpSession session) {
        if (user.getPhone()!=null){
            List<User> users = userService.Login(user);
            if (users.size()>0){
                for (User userRight : users) {
                    System.out.println(userRight.getLastLoginTime());
                session.setAttribute("user",userRight);
                }
                return "forward:";
            }
        }
        return "login";
    }

    @RequestMapping("/register")
    public String register (){
        return "register";
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public String userRegister (String phone,String loginPassword){
        int i = userService.insertUser(phone, loginPassword);
        if (i!=0){
            return "手机已经存在";
        }else {
            return "注册成功";
        }
    }



}
