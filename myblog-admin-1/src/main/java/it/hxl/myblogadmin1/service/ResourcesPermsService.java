package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.ResourcesPerms;

import java.util.List;

public interface ResourcesPermsService {

    /**
     * 根据resources的id查找该resources对应的ResourcesPerms
     * @param id
     * @return
     */
    List<ResourcesPerms> findPermissionsByResId(int id);

}
