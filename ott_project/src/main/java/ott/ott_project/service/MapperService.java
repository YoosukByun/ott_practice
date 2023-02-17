package ott.ott_project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ott.ott_project.domain.Mapper;
import ott.ott_project.domain.Member;
import ott.ott_project.domain.Ott_info;
import ott.ott_project.repository.MapperRepository;

@Service
public class MapperService {

    @Autowired
    public MapperRepository mapperRepository;

    public String OttApply(int member_Id_key, int ott_Id_key)
    {
        Ott_info ott_info = new Ott_info();


        return "";
    }
}
