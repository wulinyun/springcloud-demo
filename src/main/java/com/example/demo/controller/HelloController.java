package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 伍林云
 * @date 2019/7/17  17:22
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public  String test(){
        return "88888888";
    }
}
