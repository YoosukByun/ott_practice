package ott.ott_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.ott_project.domain.Member;
import ott.ott_project.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    public Member login(String loginId, String password) {
        Member member = new Member();
        member = memberRepository.findByUserid(loginId);
        System.out.println(member.getUserid());
        if(loginId.equals(member.getUserid())) {
            if(password.equals(member.getPw()))
            {
                return member;
            } else {
                return null;
            }
        } else {
            return null; // 여기는 로그인 실패에 대한 익셉션 처리가 필요함
        }
    }
    /**
     * @return null 로그인 실패
     */

}
