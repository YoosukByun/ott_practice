package ott.ott_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.ott_project.domain.Mapper;
import ott.ott_project.domain.Ottinfo;
import java.util.List;
import java.util.Map;

public interface MapperRepository extends JpaRepository<Mapper, Integer> {


    <Optional>Mapper deleteByMemberAndOttInfo(int member, int ottInfo);
    <Optional>Mapper deleteByOttInfo(int ottInfo);
    <Optional>Mapper deleteByMapNum(int MapNum);
    <Optional>Mapper findByMemberAndOttInfo(int member, int ottInfo);
    List<Mapper> findByOttInfo(int ottInfo);
    List<Mapper> findAllByMember(int member);
    <Optional>Mapper deleteByMember(int member);
}
