package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.Permissions;

public interface PermissionsService {
    /**
     * 根据id查找permissions
     * @return
     */
    Permissions findPermissionsById(int id);

}
