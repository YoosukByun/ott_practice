package ott.ott_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ott.ott_project.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByUserid(String useid);
    List<Member> findAllByUserid(String useid);
    Optional<Member> findByName(String name);
    Member findByPw(String pw);

}
