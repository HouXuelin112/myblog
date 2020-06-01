package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.Admin;
import it.hxl.myblogadmin1.mapper.provider.AdminProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AdminMapper {

    @Select("select * from admin")
    List<Admin> findAllAdmins();

    @Select("select * from admin where adminName=#{adminName} and password=#{password}")
    Admin findAdminByNameAndPsd(String adminName, String password);

    @Select("select * from admin where id = #{id}")
    Admin findAdminById(int id);

    @UpdateProvider(type = AdminProvider.class, method = "updateAdmin")
    @Transactional
    int updateAdmin(Admin admin);

}
