package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.Permissions;
import it.hxl.myblogadmin1.mapper.PermissionsMapper;
import it.hxl.myblogadmin1.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permissionsService")
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Override
    public Permissions findPermissionsById(int id) {
        return permissionsMapper.findPermissionsById(id);
    }
}
