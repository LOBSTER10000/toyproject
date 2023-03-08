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

    @GetMapping("/join")
    public String go3(){return "join";};

    @GetMapping("/notice")
    public String go4(){return "notice";}

    @GetMapping("/qna")
    public String go5(){return "qna";}

    @GetMapping("/view")
    public String go6(){return "view";}

    @GetMapping("/myPage")
    public String go7(){return "/myPage";}
}
