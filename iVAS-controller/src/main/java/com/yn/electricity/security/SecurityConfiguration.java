package com.yn.electricity.security;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: SecurityConfig
 * @Author: zzs
 * @Date: 2021/1/20 17:09
 * @Description:
 */
@Configuration
public class SecurityConfiguration {

    /**
     * Shiro 的Web过滤器Factory 命名:shiroFilter
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManagers) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManagers);
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new InterceptorHandler());
        shiroFilterFactoryBean.setFilters(filterMap);
        /*定义shiro过滤链  Map结构
         * Map中key(xml中是指value值)的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的
         * anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种
         * authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //过滤pas的访问
        filterChainDefinitionMap.put("/pas/**" , "anon");

        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/muban.xls", "anon");
        filterChainDefinitionMap.put("/support/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        // 下载
        filterChainDefinitionMap.put("/user/download_list.json", "anon");
        filterChainDefinitionMap.put("/log_info/log_info_download.json", "anon");

        //swagger 过滤
        filterChainDefinitionMap.put("/**/swagger-ui/**","anon");
        filterChainDefinitionMap.put("/**/swagger-resources/**","anon");
        filterChainDefinitionMap.put("/**/v3/**","anon");

        filterChainDefinitionMap.put("/user_login/login.json", "anon");
        filterChainDefinitionMap.put("/user/register.json", "anon");
        filterChainDefinitionMap.put("/error", "anon");
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理器
     */
    @Bean("securityManagers")
    public SecurityManager securityManagers() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(informationRealm());
        securityManager.setAuthenticator(authenticator());
        securityManager.setAuthorizer(authorizer());
        securityManager.setSessionManager(sessionManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean("authenticator")
    public Authenticator authenticator(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(informationRealm());
        return defaultWebSecurityManager;
    }
    @Bean("authorizer")
    public Authorizer authorizer(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(informationRealm());
        return defaultWebSecurityManager;
    }

    /**
     * 会话管理器
     * @return
     */
    @Bean("sessionManager")
    public SessionManager sessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(-1);
        return defaultWebSessionManager;
    }

    /**
     * Shiro Realm 继承自AuthorizingRealm的自定义Realm,即指定Shiro验证用户登录的类为自定义的
     */
    @Bean("informationRealm")
    public InformationRealm informationRealm(){
        InformationRealm realm = new InformationRealm();
        realm.setCredentialsMatcher(credentialsMatcher());
        return realm;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * 可以扩展凭证匹配器，实现 输入密码错误次数后锁定等功能，下一次
     */
    @Bean("credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        matcher.setHashAlgorithmName("MD5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        matcher.setHashIterations(2);
        // storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启 Shiro Spring AOP 权限注解
     * @return
     */
    @Bean("authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManagers());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 不配置SimpleCookie当 rememberMe=true时第一次登陆会打印一个异常信息但不影响正常登陆
     * @return
     */
    @Bean("rememberMeCookie")
    public SimpleCookie rememberMeCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setVersion(1);
        cookie.setName("Cookie");
        cookie.setHttpOnly(true);
        // 浏览器有效期30天，如需关闭浏览器需要重新登录使用-1
        cookie.setMaxAge(2592000);
        return cookie;
    }

    /**
     * rememberMe管理器 不配置CookieRememberMeManager当 rememberMe=true时第一次登陆会打印一个异常信息但不影响正常登陆
     * ASE加密
     * @return
     */
    @Bean("rememberMeManager")
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

}
