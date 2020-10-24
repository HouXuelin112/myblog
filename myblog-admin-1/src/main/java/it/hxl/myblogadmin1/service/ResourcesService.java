package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.Resources;

import java.util.List;

public interface ResourcesService {
    /**
     * 查找所有资源
     * @return
     */
    List<Resources> findAllResources();
}
