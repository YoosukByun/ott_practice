package ott.ott_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.ott_project.domain.Mapper;
import ott.ott_project.domain.Member;
import ott.ott_project.domain.Ottinfo;
import java.util.List;

public interface OttinfoRepository extends JpaRepository<Ottinfo, Integer> {
    List<Ottinfo> findAll();
    List<Ottinfo> findAllByOwner(String owner);
    List<Ottinfo> findAllByNowNum(int nowNum);
}
