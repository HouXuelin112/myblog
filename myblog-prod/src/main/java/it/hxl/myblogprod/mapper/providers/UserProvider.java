package it.hxl.myblogprod.mapper.providers;

import it.hxl.myblogprod.entity.Users;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    public String updateUser(Users user){
        return new SQL(){
            {
                UPDATE("users");
                if (user.getEmail() != null && !"".equals(user.getEmail())){
                    SET("email = #{email}");
                }
                if (user.getPassword() != null && !"".equals(user.getPassword())){
                    SET("password = #{password}");
                }
                if (user.getHead() != null){
                    SET("head = #{head}");
                }
                if (user.getPhone() != null && !"".equals(user.getPhone())){
                    SET("phone = #{phone}");
                }
                if (user.getNickName() != null && !"".equals(user.getNickName())){
                    SET("nickName = #{nickName}");
                }
                if (user.getBirthday() != null){
                    SET("birthday = #{birthday}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }

}
