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
            return "home";
        } else {

        }
        model.addAttribute("member",loginmember);
        return "loginhome";
    }
    public String home() {
        return "home";
    }
}