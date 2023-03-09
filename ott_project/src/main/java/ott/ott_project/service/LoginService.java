package ott.ott_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.ott_project.domain.Member;
import ott.ott_project.repository.LoginRepository;
import ott.ott_project.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        Member member = new Member();
        try {
            member = memberRepository.findByUserid(loginId).orElseThrow();
        } catch (Exception e) {
            return null;
        }
        System.out.println(loginId);
        if (loginId.equals(member.getUserid())) {
            if (password.equals(member.getPw())) {
                return member;
            } else {
                return null;
            }

        }else {
            return null;
        }
    }
}
