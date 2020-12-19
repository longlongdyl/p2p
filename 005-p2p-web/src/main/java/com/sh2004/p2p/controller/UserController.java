package com.sh2004.p2p.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.CommonResponse;
import com.sh2004.p2p.eneity.User;
import com.sh2004.p2p.service.UserService;
import com.sh2004.p2p.util.Aliyun;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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
            User loginUser = userService.Login(user);
            if (loginUser.getId() != null){
                session.setAttribute("user",loginUser);
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

        return userService.insertUser(phone, loginPassword);
    }

    @RequestMapping("/registerCode")
    @ResponseBody
    public String registerCode (String code,String phone){
        System.out.println(code+phone);
        if (code.equals("123456")){
            return "1";
        }
        return "0";
    }

    @RequestMapping("/loan/logout")
    public String logout (HttpSession session){
        session.removeAttribute("user");
        return "forward:/index";
    }


}
