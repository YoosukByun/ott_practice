package ott.ott_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ott.ott_project.domain.Member;
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

    @GetMapping("/login") //로그인
    public String Login(@RequestParam("userid") String userId, @RequestParam("userpw") String userPw, Model model)
    {
        model.addAttribute("userid", userId);
        model.addAttribute("userpw", userPw);
        boolean login_result=memberService.loginProcess(userId, userPw);
        if(login_result) {
           return "ott/ottView";
        }
       else {
            return "members/Login";
        }
    }
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
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

    /*@GetMapping("/memberlist") //전체 멤버 조회
    public String List(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "member/memberList";
    }*/
    /*@GetMapping("/members") //전체 멤버 조회
    public List<Member> getMembers() {
        return memberService.findMembers();
    }*/
}
