package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.ResourcesPerms;
import it.hxl.myblogadmin1.mapper.ResourcesPermsMapper;
import it.hxl.myblogadmin1.service.ResourcesPermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("resourcesPermsService")
public class ResourcesPermsServiceImpl implements ResourcesPermsService {

    @Autowired
    private ResourcesPermsMapper resourcesPermsMapper;

    @Override
    public List<ResourcesPerms> findPermissionsByResId(int id) {
        return resourcesPermsMapper.findResourcesPermsByResId(id);
    }
}
