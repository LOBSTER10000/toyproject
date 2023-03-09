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
    public String login(){return "login";}

    @GetMapping("/join")
    public String join(){return "join";};

    @GetMapping("/notice")
    public String notice(){return "notice";}

    @GetMapping("/qna")
    public String qna(){return "qna";}

    @GetMapping("/view")
    public String view(){return "view";}

    @GetMapping("/myPage")
    public String myPage(){return "/myPage";}

    @GetMapping("/registration")
    public String registration(){return "/registration";}

    @GetMapping("/modify")
    public String modify(){return "/modify";}

    @GetMapping("/bbs")
    public String bbs(){return "/bbs";}

    @GetMapping("/cart")
    public String cart(){return "/cart";}

    @GetMapping("/write")
    public String add(){return "/write";}

    @GetMapping("/update")
    public String update(){return "/update";}

    @GetMapping("/mainView")
    public String mainView(){return "/mainView";}
}
