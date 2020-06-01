package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.AdminLogin;

import java.util.List;

/**
 *处理 管理员登录信息
 */
public interface AdminLoginService {

    int insertAdminLogin(AdminLogin adminLogin);

    List<AdminLogin> findAdminLoginById(int id);

    int getLoginCountByAdminId(int id);

}
