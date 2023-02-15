package ott.ott_project;
import org.junit.jupiter.api.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ott.ott_project.domain.Member;
import ott.ott_project.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest

public class MemberRepositoryTests {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void create() {
        Member member = new Member();
        member.setId("dbtjr1657");
        member.setName("yoosuk");
        member.setPw("1657");
        member.setPhone_num(1026301657);
        member.setAccount("신한91026301657");
        memberRepository.save(member);
    }

}
