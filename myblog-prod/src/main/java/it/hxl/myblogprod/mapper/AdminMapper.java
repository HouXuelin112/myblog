package it.hxl.myblogprod.mapper;

import it.hxl.myblogprod.entity.Admin;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    @Select("select * from admin where id=#{id}")
    Admin findAdminById(int id);

}
