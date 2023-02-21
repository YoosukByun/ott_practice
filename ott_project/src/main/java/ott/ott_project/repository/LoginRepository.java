package ott.ott_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ott.ott_project.domain.Login;
import ott.ott_project.domain.Member;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    <Optional>Login deleteByUserid(String userid);
    <Optional>Login findByUserid(String userid);
}
