package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.AdminLogin;
import it.hxl.myblogadmin1.mapper.AdminLoginMapper;
import it.hxl.myblogadmin1.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminLoginService")
public class AdminLoginServiceImpl implements AdminLoginService {
    @Autowired
    private AdminLoginMapper adminLoginMapper;


    @Override
    public int insertAdminLogin(AdminLogin adminLogin) {
        return adminLoginMapper.insertAdminLogin(adminLogin);
    }

    @Override
    public List<AdminLogin> findAdminLoginById(int id) {
        return adminLoginMapper.findAdminLoginsByAdminId(id);
    }

    @Override
    public int getLoginCountByAdminId(int id) {
        return adminLoginMapper.getLoginCountByAdminId(id);
    }
}
