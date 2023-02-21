package ott.ott_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ott.ott_project.service.MemberService;

import java.util.List;
@Controller
//@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/CreateMemberForm";
    }

    @PostMapping("/members/new") //회원가입
    public String Create(
            @RequestParam("name") String Name,
            @RequestParam("userid") String userId,
            @RequestParam(value = "userpw") String userPw,
            @RequestParam(value = "phonenum") int phoneNum,
            @RequestParam(value = "account") String Account,
            Model model){
        model.addAttribute("name", Name);
        model.addAttribute("userid", userId);
        model.addAttribute("userpw", userPw);
        model.addAttribute("phonenum", phoneNum);
        model.addAttribute("account", Account);
        memberService.memberRegister(userId, userPw, Name, phoneNum, Account);
        return "redirect:/";
    }
}
