package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.Admin;

import java.util.List;

public interface AdminService {
    /**
     * 验证管理员
     * @param admin
     * @return
     */
    Admin validateAdmin(Admin admin);

    Admin getAdminByName(String adminName);

    int updateAdmin(Admin admin);

    Admin findAdminById(int id);

    List<Admin> findAllAdmins();

}
