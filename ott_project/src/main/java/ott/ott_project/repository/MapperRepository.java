package ott.ott_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.ott_project.domain.Mapper;
import ott.ott_project.domain.Ottinfo;

import java.util.List;

public interface MapperRepository extends JpaRepository<Mapper, Integer> {
    Mapper deleteByMemberAndOttInfo(int member, int ottInfo);
    Mapper deleteByOttInfo(int ottInfo);
}
