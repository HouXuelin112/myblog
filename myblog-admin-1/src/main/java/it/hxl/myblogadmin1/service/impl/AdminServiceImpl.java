package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.Admin;
import it.hxl.myblogadmin1.entity.AdminLogin;
import it.hxl.myblogadmin1.mapper.AdminLoginMapper;
import it.hxl.myblogadmin1.mapper.AdminMapper;
import it.hxl.myblogadmin1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.AnnotationMirror;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin validateAdmin(Admin admin) {
        return adminMapper.findAdminByNameAndPsd(admin.getAdminName(), admin.getPassword());
    }

    @Override
    public Admin getAdminByName(String adminName) {
        return adminMapper.findAdminByName(adminName);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public Admin findAdminById(int id) {
        return adminMapper.findAdminById(id);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminMapper.findAllAdmins();
    }
}
