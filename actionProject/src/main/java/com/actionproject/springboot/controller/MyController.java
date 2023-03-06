package com.actionproject.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String go(){
        return "index2";
    }

    @GetMapping("/login")
    public String go2(){return "login";}
}
