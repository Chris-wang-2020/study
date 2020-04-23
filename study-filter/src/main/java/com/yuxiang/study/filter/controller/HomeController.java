package com.yuxiang.study.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/3/30 11:13 上午
 */
@RestController
public class HomeController {

    @GetMapping("/go")
    public String go() {
        return "/go";
    }
}
