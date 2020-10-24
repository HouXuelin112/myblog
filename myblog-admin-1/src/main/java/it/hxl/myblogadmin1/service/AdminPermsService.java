package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.AdminPerms;

import java.util.List;

public interface AdminPermsService {

    /**
     * 根据adminId获取AdminPerms
     * @param adminId 从admin出获得
     * @return AdminPerms d的列表
     */
    List<AdminPerms> findAdminPermsByAdminId(int adminId);

}
