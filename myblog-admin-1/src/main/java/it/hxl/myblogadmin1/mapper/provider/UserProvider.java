package it.hxl.myblogadmin1.mapper.provider;

import it.hxl.myblogadmin1.entity.Users;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    public String updateUser(Users users){
        return new SQL(){
            {
                UPDATE("users");
                if (users.getUsername() != null && !users.getUsername().equals("")){
                    SET("username=#{username}");
                }
                if (users.getPassword() != null && !users.getPassword().equals("")){
                    SET("password=#{password}");
                }
                if (users.getPhone() != null && !users.getPhone().equals("")){
                    SET("phone=#{phone}");
                }
                if (users.getEmail() != null && !users.getEmail().equals("")){
                    SET("email=#{email}");
                }
                if (users.getNickName() != null && !users.getNickName().equals("")){
                    SET("nickName=#{nickName}");
                }
                if (users.getHead() != null){
                    SET("head=#{head}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }

}
