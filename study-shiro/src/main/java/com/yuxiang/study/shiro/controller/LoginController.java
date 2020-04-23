package com.yuxiang.study.shiro.controller;

import com.yuxiang.study.shiro.entity.LoginReq;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/3/27 2:39 下午
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody LoginReq loginReq) {
        AuthenticationToken token = new UsernamePasswordToken(loginReq.getUsername(), loginReq.getUsername());
        SecurityUtils.getSubject().login(token);
        return "ok";
    }
}
