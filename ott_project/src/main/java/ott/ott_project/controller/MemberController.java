package ott.ott_project.controller;

import org.hibernate.action.internal.EntityAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ott.ott_project.domain.Login;
import ott.ott_project.domain.Member;
import ott.ott_project.domain.Ottinfo;
import ott.ott_project.repository.LoginRepository;
import ott.ott_project.repository.MemberRepository;
import ott.ott_project.repository.OttinfoRepository;
import ott.ott_project.service.MapperService;
import ott.ott_project.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
//@RestController
public class MemberController {
    private final MemberService memberService;
    private final LoginRepository loginRepository;
    private final MemberRepository memberRepository;
    private final MapperService mapperService;
    private final OttinfoRepository ottinfoRepository;

    @Autowired
    public MemberController(MemberService memberService,
                            LoginRepository loginRepository,
                            MemberRepository memberRepository,
                            MapperService mapperService,
                            OttinfoRepository ottinfoRepository) {
        this.memberService = memberService;
        this.loginRepository = loginRepository;
        this.memberRepository = memberRepository;
        this.mapperService = mapperService;
        this.ottinfoRepository = ottinfoRepository;
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

    @GetMapping("/members/myinfo")
    public String MyInfo(Model model, HttpServletRequest request) {
//      List<Login> logins = loginRepository.findAll();
//      Login login = logins.get(0);
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(LoginController.SessionConst.LOGIN_MEMBER);
        //쿠키값 받으면 쿠키값 받아서 고치는걸로 고쳐야함.
        //화면 만들라고 만들어놓은거임
        //Member member = memberRepository.findByUserid(login.getUserid());
        model.addAttribute("member", loginMember);
        List<Ottinfo> ottInfos =mapperService.showOttinfoByMember(loginMember.getMemIdKey());
        model.addAttribute("ottinfos", ottInfos);
        return "members/myinfo";
    }
}
