package com.ljl.brillim.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("get")
    public String get(){
        return "controller ceshi !";
    }
}
