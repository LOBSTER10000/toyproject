package com.actionproject.springboot.controller;


import com.actionproject.springboot.entity.User;
import com.actionproject.springboot.repository.UserRepository;
import com.actionproject.springboot.repository.ViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final UserRepository ur;
    private final ViewRepository vr;

    @GetMapping("/")
    public String go(HttpSession httpSession){
        httpSession.getAttribute("user");
        return "index2";
    }

    @GetMapping("/index2")
    public String index2(){return "index2";}
    @GetMapping("/login")
    public String login(){return "login";}

    @PostMapping("/login")
    public String login2(User user, HttpSession httpSession){
        User userDB = ur.selLogin(user.getUserId(), user.getUserPass());
        System.out.println(userDB);
        if(userDB != null){
            System.out.println(userDB);
            httpSession.setAttribute("user", userDB);
            return "redirect:index2";
        }
        else {
            System.out.println("실패했습니다");
            return "/login";
        }
    }
    @GetMapping("/join")
    public String join(){return "join";};

    @PostMapping("/join")
    public String join2(User user){
        ur.insertJoin(user.getUserId(), user.getUserEmail(), user.getUserPass(), user.getUserZip(), user.getUserAdd());
        return "redirect:index2";
    }

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

    @GetMapping("/write2")
    public String add2(){return "/write2";}

    @GetMapping("/update")
    public String update(){return "/update";}

    @GetMapping("/mainView")
    public String mainView(){return "/mainView";}
}
