package ott.ott_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ott.ott_project.domain.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        Member loginmember = (Member) session.getAttribute(LoginController.SessionConst.LOGIN_MEMBER);

        if (loginmember == null) {
            // 다시 로그인 처리가 가능하게끔 하거나
            // 로그인이 안된 상태라는 노티를 보여주는 페이지 이동 처리
//            return "loginhome";
        } else {
            // 정상 로그인 됐을 때 이동하는 페이지로 return
        }
        model.addAttribute("member",loginmember);
        return "loginhome";
    }
    public String home() {
        return "home";
    }
}