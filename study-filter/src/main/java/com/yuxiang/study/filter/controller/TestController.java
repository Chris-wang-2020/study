package com.yuxiang.study.filter.controller;

import com.yuxiang.study.filter.anotation.MyAnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyx-l
 * @version 1.0
 * @date 2020/3/30 11:14 上午
 */
@RestController
public class TestController {

    @GetMapping("/test")
    @MyAnotation("anotation")
    public String test() {
        return "test";
    }}
