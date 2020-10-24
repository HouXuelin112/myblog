package it.hxl.myblogadmin1.shiro;

import cn.hutool.core.util.StrUtil;
import it.hxl.myblogadmin1.entity.Permissions;
import it.hxl.myblogadmin1.entity.Resources;
import it.hxl.myblogadmin1.entity.ResourcesPerms;
import it.hxl.myblogadmin1.service.PermissionsService;
import it.hxl.myblogadmin1.service.ResourcesPermsService;
import it.hxl.myblogadmin1.service.ResourcesService;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro 配置类
 * 1. 创建Realm
 * 2. 创建SecurityManager
 * 3. 创建ShiroFilterFactoryBean
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private ResourcesService resourcesService;
    @Autowired
    private ResourcesPermsService resourcesPermsService;
    @Autowired
    private PermissionsService permissionsService;

//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//        filterRegistration.setEnabled(true);
//        filterRegistration.addUrlPatterns("/*");
//        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
//        return filterRegistration;
//    }

    /**
     * 创建ShiroFilterFactoryBean
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器，实现权限相关的url拦截
        /**
         * 常见过滤器：
         * anon：无需认证（登录）可以访问
         * authc：必须认证才可以访问
         * user:如果使用Remember Me的功能，可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * roles:该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //设置 /login 、 /adminLogin 不拦截
        filterMap.put("/login", "anon");
        filterMap.put("/adminLogin", "anon");
        filterMap.put("/logout", "anon");
        //设置静态资源不拦截
        filterMap.put("/bootstrap*/**", "anon");
        filterMap.put("/error/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/js/**", "anon");

        //将需要收授权的资源放入map
        filterMap.putAll(getPermissionsMap());

        //其他路径皆需要认证
        filterMap.put("/**", "authc");
        //设置登陆路径
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/unAuthorize");
        //添加到过滤链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        System.out.println("shiroFilterFactoryBean");
        return shiroFilterFactoryBean;
    }

    /**
     * 查询需授权的资源放入map
     * @return 资源权限的map
     */
    private Map<String, String> getPermissionsMap(){
        Map<String, String> permsMap = new LinkedHashMap<>();
        //获取所有数据库中需授权的资源
        List<Resources> resources = resourcesService.findAllResources();
        for (Resources res: resources){
            //根据res的id获取perms的id
            List<ResourcesPerms> resPerms = resourcesPermsService.findPermissionsByResId(res.getId());
            StringBuilder sb = new StringBuilder();
            for (ResourcesPerms resPerm: resPerms){
                //根据perms的id获取perms
                Permissions permissions = permissionsService.findPermissionsById(resPerm.getPermsId());
                sb.append(permissions.getPerms());
                sb.append(",");
            }
            String permsStr = sb.toString();
            if (permsStr.length() > 0){
                //去掉最后一个 “ , ”
                permsStr = permsStr.substring(0, permsStr.length() - 1);
            }
            //将资源url与所需权限放入资源权限的map

            permsMap.put(res.getUrl(), StrUtil.format("perms[{}]", permsStr));
        }
        return permsMap;
    }

    /**
     * 创建SecurityManager
     * @param userRealm
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
        cookieRememberMeManager.setCookie(simpleCookie());
        return cookieRememberMeManager;
    }

    @Bean("rememberMeCookie")
    public SimpleCookie simpleCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        return cookie;
    }

    /**
     * 创建Realm
     * @return UserRealm
     */
    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(2);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

//    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro注解支持
     * @return
     */
    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 开启aop注解
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
