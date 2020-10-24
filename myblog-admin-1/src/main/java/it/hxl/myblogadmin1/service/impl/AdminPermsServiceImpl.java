package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.AdminPerms;
import it.hxl.myblogadmin1.mapper.AdminPermissionMapper;
import it.hxl.myblogadmin1.service.AdminPermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminPermsService")
public class AdminPermsServiceImpl implements AdminPermsService {

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    @Override
    public List<AdminPerms> findAdminPermsByAdminId(int adminId) {
        return adminPermissionMapper.findAdminPermsByAdminId(adminId);
    }
}
