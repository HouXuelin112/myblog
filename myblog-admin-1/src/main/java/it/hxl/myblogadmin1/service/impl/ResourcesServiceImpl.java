package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.Resources;
import it.hxl.myblogadmin1.mapper.ResourcesMapper;
import it.hxl.myblogadmin1.mapper.ResourcesPermsMapper;
import it.hxl.myblogadmin1.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public List<Resources> findAllResources() {
        return resourcesMapper.findAllResources();
    }
}
