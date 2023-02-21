package ott.ott_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ott.ott_project.domain.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {


    //Member findByName(String id);
    Member findByUserid(String useid);
    Optional<Member> findByName(String name);

    Member findByPw(String pw);
    //Optional<Member> findByName(@Param("name") String name);
    //@Query(value = "SELECT ID FROM Member WHERE name = ?0", nativeQuery = true)


}
