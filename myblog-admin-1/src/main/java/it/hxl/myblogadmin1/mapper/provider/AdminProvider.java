package it.hxl.myblogadmin1.mapper.provider;

import it.hxl.myblogadmin1.entity.Admin;
import org.apache.ibatis.jdbc.SQL;

public class AdminProvider {

    public String updateAdmin(Admin admin){
        return new SQL(){
            {
                UPDATE("admin");
                if (admin.getAdminName() != null && !admin.getAdminName().equals("")){
                    SET("adminName = #{adminName}");
                }
                if (admin.getPassword() != null && !admin.getPassword().equals("")){
                    SET("password = #{password}");
                }
                if (admin.getRealName() != null && !admin.getRealName().equals("")){
                    SET("realName = #{realName}");
                }
                if (admin.getPhone() != null && !admin.getPhone().equals("")){
                    SET("phone = #{phone}");
                }
                if (admin.getEmail() != null && !admin.getEmail().equals("")){
                    SET("email = #{email}");
                }
                if (admin.getNickName() != null && !admin.getNickName().equals("")){
                    SET("nickName = #{nickName}");
                }
                if (admin.getHead() != null){
                    SET("head = #{head}");
                }
                if (admin.getSalt() != null){
                    SET("salt = #{salt}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }

}
