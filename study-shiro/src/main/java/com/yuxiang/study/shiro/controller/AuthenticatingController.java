package com.yuxiang.study.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/3/27 2:37 下午
 */
@RestController
public class AuthenticatingController {

    @GetMapping("/test")
    public String authenticating() {
        return "authenticating ok";
    }

    @GetMapping("/session")
    public String session() {
        return "session ok";
    }

    @GetMapping("/token")
    public String token() {
        return "token ok";
    }
}
