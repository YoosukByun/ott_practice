package ott.ott_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.ott_project.domain.Mapper;

public interface MapperRepository extends JpaRepository<Mapper, Integer> {
}
