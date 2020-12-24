package com.sh2004.p2p.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import com.sh2004.p2p.eneity.User;
import com.sh2004.p2p.result.Result;
import com.sh2004.p2p.service.UserService;
import com.sh2004.p2p.util.Aliyun;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    @Reference(interfaceClass = UserService.class, timeout = 20000, version = "1.0.0")
    private UserService userService;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;


    @RequestMapping("/login")
    public String login(User user , HttpSession session, String url) {
        if (user.getPhone() != null) {
            User loginUser = userService.Login(user);
            if (null != loginUser.getId()) {
                loginUser.setLastLoginTime(new Date());
                session.setAttribute("user", loginUser);
                if (null == url){
                url = (String) session.getAttribute("backUrl");
                }
                System.out.println(url);
                return "redirect:"+url ;
            }
        }
        session.setAttribute("backUrl",url);
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public Result userRegister(String phone, String loginPassword) {

        return userService.insertUser(phone, loginPassword);
    }

    @RequestMapping("/registerCode")
    @ResponseBody
    public String registerCode(String code, String phone, String randomCode) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        String messageCode = (String) redisTemplate.opsForValue().get(phone);
        if (null == messageCode) {
            if (null != randomCode) {
                redisTemplate.opsForValue().set(phone, randomCode, 1, TimeUnit.MINUTES);
                CommonResponse result = Aliyun.code(phone, randomCode);
            } else {
                return "2";
            }
        } else {
            if (StringUtils.equals(code, messageCode)) {
                return "1";
            }
        }
        return "0";
    }

    @RequestMapping("/loan/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/index";
    }

    @RequestMapping("/realName")
    public String realName() {

        return "realName";
    }

}
