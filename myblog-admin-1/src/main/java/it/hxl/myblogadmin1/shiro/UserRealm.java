package it.hxl.myblogadmin1.shiro;

import it.hxl.myblogadmin1.entity.Admin;
import it.hxl.myblogadmin1.entity.AdminPerms;
import it.hxl.myblogadmin1.entity.Permissions;
import it.hxl.myblogadmin1.service.AdminPermsService;
import it.hxl.myblogadmin1.service.AdminService;
import it.hxl.myblogadmin1.service.PermissionsService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义 Realm 类继承 AuthorizingRealm
 */
//@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminPermsService adminPermsService;
    @Autowired
    private PermissionsService permissionsService;

    /**
     * 指定授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        String username = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //编写授权逻辑
        Admin admin = adminService.getAdminByName(username);
        //根据admin的id获取AdminPerms
        List<AdminPerms> adminPerms = adminPermsService.findAdminPermsByAdminId(admin.getId());
        Set permsSet = new HashSet<String>();
        for (AdminPerms ap: adminPerms){
            //根据perms的id获取perms
            Permissions permissions = permissionsService.findPermissionsById(ap.getPermsId());
            permsSet.add(permissions.getPerms());
        }
        authorizationInfo.setStringPermissions(permsSet);
        return authorizationInfo;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        Admin admin = adminService.getAdminByName(token.getUsername());
        if (admin == null){
            return null; //shiro底层会抛出一个UnknownAccountException
        }

        //验证密码
        SimpleAuthenticationInfo authenticationInfo =  new SimpleAuthenticationInfo(token.getUsername(), admin.getPassword(), ByteSource.Util.bytes(admin.getSalt()), getName());
        return authenticationInfo;
    }
}
