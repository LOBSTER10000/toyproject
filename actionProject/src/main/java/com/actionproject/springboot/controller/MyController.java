package com.actionproject.springboot.controller;


import com.actionproject.springboot.entity.Registration;
import com.actionproject.springboot.entity.Registration2;
import com.actionproject.springboot.entity.User;
import com.actionproject.springboot.entity.cs.View;
import com.actionproject.springboot.entity.cs.View2;
import com.actionproject.springboot.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final UserRepository ur;
    private final ViewRepository vr;
    private final View2Repository vr2;

    private final RegistrationRepository rg;

    private final Registration2Repository r2g;

    private final EntityManager entityManager;
    @GetMapping("/")
    public String go(HttpSession httpSession){
        httpSession.getAttribute("user");
        return "index2";
    }

    @GetMapping("/index2")
    public String index2(){

        return "index2";}
    @GetMapping("/login")
    public String login(){return "login";}

    @PostMapping("/login")
    public String login2(User user, HttpSession httpSession, Model mo){
        User userDB = ur.selLogin(user.getUserId(), user.getUserPass());
        System.out.println(userDB);
        if(userDB != null){
            System.out.println(userDB);
            httpSession.setAttribute("user", userDB);
            mo.addAttribute("alert", "로그인이 성공했습니다");
            mo.addAttribute("url", "/index2");
            return "/alert";
        }
        else {
            System.out.println("실패했습니다");
            mo.addAttribute("alert", "로그인에 실패했습니다");
            mo.addAttribute("url", "/login");
            return "/alert";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession, Model mo){
        httpSession.setAttribute("user", null);
        mo.addAttribute("alert", "로그아웃 하셨습니다");
        mo.addAttribute("url", "/index2");
        return "/alert";
    }

    @GetMapping("/join")
    public String join(){return "join";};

    @PostMapping("/join")
    public String join2(User user){
        ur.insertJoin(user.getUserId(), user.getUserEmail(), user.getUserPass(), user.getUserZip(), user.getUserAdd());
        return "redirect:index2";
    }

    @GetMapping("/notice")
    public String notice(View view, Model mo){
        List<View> list = vr.selectAll();
        mo.addAttribute("list", list);

        return "notice";}

    @GetMapping("/qna")
    public String qna(View2 view, Model mo){
        List<View2> list = vr2.selectAll();
        mo.addAttribute("list", list);
        return "qna";}

    @GetMapping("/view")
    public String view(HttpSession session, Model mo, View view){
        session.getAttribute("user");
        String user = String.valueOf(session.getAttribute("user"));
        mo.addAttribute("user", user);
        View list2 = vr.selectOne(view.getNoticeNumber());
        mo.addAttribute("list2", list2);
        System.out.println(list2);
        return "view";}

    @GetMapping("/view2")
    public String view2(HttpSession session, Model mo, View2 view2){
        session.getAttribute("user");
        String user = String.valueOf(session.getAttribute("user"));
        mo.addAttribute("user", user);
        View2 list2 = vr2.selectOne(view2.getQnaNumber());
        mo.addAttribute("list2", list2);
        System.out.println(list2);
        System.out.println("session user값" + user);
        return "view2";}

    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model mo){
        User user = (User) session.getAttribute("user");
        mo.addAttribute("user", user);
        if(session.getAttribute("user") != null){
            System.out.println("로그인이 되어있습니다");
            return "/myPage";
        }
        else{
            mo.addAttribute("alert","로그인을 하세요");
            mo.addAttribute("url", "/login");
            return "/alert";
        }
    }

    @GetMapping("/registration")
    public String registration(HttpSession session, Model mo){
        if(session.getAttribute("user") != null){
            System.out.println("로그인이 되어있습니다");
            User user = (User) session.getAttribute("user");
            mo.addAttribute("user", user);
            return "/registration";
        }
        else{
            mo.addAttribute("alert","로그인을 하세요");
            mo.addAttribute("url", "/login");
            return "/alert";
        }
    }

    @PostMapping("/registration")
    public String registration2(Registration re, @RequestParam("files") MultipartFile[] files, Registration2 re2, HttpServletResponse response) {
        List<MultipartFile> imageFiles = new ArrayList<>();
        int fileCount = 0; // 업로드한 파일

        int up = rg.insertRe(re.getProHead(), re.getProFrom(), re.getProStartMoney(), re.getProUnitMoney(), re.getProDirectMoney(), re.getProPresentMoney() ,re.getProContent(), re.getProDate(), re.getImagesExist(), re.getUserId());
        System.out.println(re);
        System.out.println(up);

        for (MultipartFile file : files) {
            if (file.getContentType().startsWith("image/")) {
                if (fileCount >= 3) { // 이미 3개 이상 업로드한 경우
                    response.setContentType("text/html; charset=UTF-8"); // 응답 형식 지정
                    PrintWriter out;
                    try {
                        out = response.getWriter();
                        out.println("<script>alert('이미지 3개 이상 업로드는 불가합니다.');</script>"); // 경고창 출력
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return "redirect:index2"; // 페이지 이동하지 않음
                }
                imageFiles.add(file);
                fileCount++;
            }
        }

        for (MultipartFile imageFile : imageFiles) {
            String fileName = imageFile.getOriginalFilename(); // 파일 이름 추출
            String uploadPath = "src/main/resources/static/images/upload/"; // 업로드 디렉토리 경로
            String filePath = uploadPath + fileName; // 저장될 파일 경로
            String uuid = UUID.randomUUID().toString();
            String realPath = uploadPath + uuid +".jpg";
            System.out.println(fileName);
            System.out.println(filePath);
            System.out.println(realPath);

            // 파일 저장
            try (OutputStream os = new FileOutputStream(realPath)) {
                os.write(imageFile.getBytes());
                re2 = new Registration2();
                re2.setFileName(uuid);
                re2.setFilePath(realPath);
                Long str = re.getProNumber();
                System.out.println("proNumber is + " + str);
                r2g.save(re2);
            } catch (IOException e) {
                // 파일 저장 실패 시 예외 처리
                e.printStackTrace();
            }
        }
        return "redirect:index2";
    }


    @GetMapping("/modify")
    public String modify(HttpSession session, Model mo){
        if(session.getAttribute("user") != null){
            System.out.println("로그인이 되어있습니다");
            User user = (User) session.getAttribute("user");
            mo.addAttribute("user", user);
            System.out.println(user);
            return "/modify";
        }
        else{
            mo.addAttribute("alert","로그인을 하세요");
            mo.addAttribute("url", "/login");
            return "/alert";
        }
    }

    @PostMapping("/modify")
    public String modify2(User user){
        int up = ur.updateUser(user.getUserPass(), user.getUserEmail(), user.getUserId());
        System.out.println(up);
        return "redirect:registration";
    }

    @GetMapping("/bbs")
    public String bbs(){return "/bbs";}

    @GetMapping("/cart")
    public String cart(HttpSession session, Model mo){
        User user = (User) session.getAttribute("user");
        mo.addAttribute("user", user);
        if(session.getAttribute("user") != null){
            System.out.println("로그인이 되어있습니다");
            return "/cart";
        }
        else{
            mo.addAttribute("alert","로그인을 하세요");
            mo.addAttribute("url", "/login");
            return "/alert";
        }
    }

    @GetMapping("/write")
    public String add(HttpSession session, Model mo){
        User user = (User) session.getAttribute("user");
        mo.addAttribute("user",user);
        return "/write";}

    @PostMapping("/write")
    public String add2(View view){
        vr.writeNo(view);

        return "redirect:notice";
    }

    @GetMapping("/write2")
    public String add3(HttpSession session, Model mo){
        User user = (User) session.getAttribute("user");
        mo.addAttribute("user", user);
        return "/write2";}


    @PostMapping("/write2")
    public String add4(View2 view2){
        vr2.writeNo2(view2);

        return "redirect:qna";
    }
    @GetMapping("/update")
    public String update(View view, Model mo){
        View list = vr.selectOne(view.getNoticeNumber());
        mo.addAttribute("list", list);

        System.out.println("업데이트시 나오는 리스트 : " +  list);
        return "/update";}

    @PostMapping("/update")
    public String update3(View view){
        System.out.println("view : " + view);
        int str = vr.updateNo(view, view.getNoticeNumber());
        System.out.println("업데이트 성공했나? :" + str);
        return "redirect:/notice";
    }

    @GetMapping("/update2")
    public String update2(View2 view2, Model mo){
        View2 list = vr2.selectOne(view2.getQnaNumber());
        if(list == null){
            System.out.println("list is null");
        }
        mo.addAttribute("list", list);
        System.out.println("업데이틋 나오는 리스트 : " + list);
        return "/update2";
    }
    @PostMapping("/update2")
    public String update4(View2 view2){
        System.out.println("view2 " + view2);
        int str = vr2.updateNo(view2.getBbsHead(), view2.getBbsContent(), view2.getQnaNumber());

        System.out.println("update2 success? :" + str);
        return "redirect:/qna";
    }

    @GetMapping("delete")
    public String delete(View view){
        int str = vr.deleteNo(view.getNoticeNumber());
        System.out.println("삭제 성공? : " + str);
        return "redirect:/notice";
    }

    @GetMapping("delete2")
    public String delete(View2 view2){
        int str = vr2.deleteNo(view2.getQnaNumber());
        System.out.println("삭제 성공? : " + str);
        return "redirect:/qna";
    }

    @GetMapping("/mainView")
    public String mainView(){return "/mainView";}
}
