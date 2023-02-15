package ott.ott_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ott.ott_project.domain.Member;
import ott.ott_project.service.MemberService;

import java.util.List;
@RestController
public class MemberViewController {

    private final MemberService memberService;

    @Autowired
    public MemberViewController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/members") //전체 멤버 조회
    public List<Member> getMembers() {
        return memberService.findMembers();
    }
}
