package ott.ott_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ott.ott_project.domain.Member;
import ott.ott_project.domain.Ottinfo;
import ott.ott_project.repository.LoginRepository;
import ott.ott_project.repository.MemberRepository;
import ott.ott_project.repository.OttinfoRepository;
import ott.ott_project.service.MapperService;
import ott.ott_project.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
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
        if (memberService.memberRegister(userId, userPw, Name, phoneNum, Account).equals("e")) {
            return "error";
        }
        //memberService.memberRegister(userId, userPw, Name, phoneNum, Account);
        return "redirect:/";
    }

    @GetMapping("/members/myinfo")
    public String MyInfo(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(LoginController.SessionConst.LOGIN_MEMBER);
        model.addAttribute("member", loginMember);
        List<Ottinfo> ottInfos = mapperService.showOttinfoByMember(loginMember.getMemIdKey());
        model.addAttribute("ottinfos", ottInfos);
        List<Member> members = new ArrayList<>();
        for(Ottinfo ottinfo : ottInfos)
        {
            Member memberTmp = memberRepository.findByName(ottinfo.getOwner()).orElseThrow();
            members.add(memberTmp);
        }
        model.addAttribute("members", members);
        return "members/myinfo";
    }

    @GetMapping("/members/quit") //화면 없고 API만 존재 -> 탈퇴 후, 바로 로그인 페이지로 이동
    public String MemberQuit(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(LoginController.SessionConst.LOGIN_MEMBER);
        //Mapper에서 정리 필요
        //Ottinfo에서 정리 필요
        mapperService.OttCancel(loginMember.getMemIdKey(),-1);//데이터 삭제
        memberService.memberQuit(loginMember.getMemIdKey());

        if(session != null){
            session.invalidate(); //로그아웃 후, 로그인 페이지로 리다이렉트
            return "redirect:/";
        }
        return "redirect:/";
    }
    @GetMapping("/members/quitcheck")
    public String MemberQuitCheck() {
        return "members/quitcheck";
    }
}
