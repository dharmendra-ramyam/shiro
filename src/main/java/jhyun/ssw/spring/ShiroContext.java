package jhyun.ssw.spring;

import com.google.common.collect.ImmutableMap;
import jhyun.ssw.spring.shiro.ShiroContextInitialize;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.*;

/**
 * Created by jhyun on 13. 12. 21.
 */
@Import(value = {ShiroContextInitialize.class})
@Configuration
public class ShiroContext {

    private static Logger logger = LoggerFactory.getLogger(ShiroContext.class);

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheManager();
    }

    @Bean
    public SessionDAO sessionDAO() {
        return new EnterpriseCacheSessionDAO();
    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        return sessionManager;
    }

    @Bean
    public SecurityManager securityManager(CacheManager cacheManager, SessionManager sessionManager) {
        DefaultWebSecurityManager sm = new DefaultWebSecurityManager();
        //
        sm.setCacheManager(cacheManager);
        sm.setSessionManager(sessionManager);
        // realm.
        IniRealm realm = new IniRealm();
        realm.addAccount("admin", "1234");
        sm.setRealm(realm);
        //
        return sm;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/a/shiro/index");
        return logoutFilter;
    }

    // NOTE:   roles.unauthorizedUrl, perms.unauthorizedUrl 같은 것들도 각각의 필터를 생성하고 설정.

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, LogoutFilter logoutFilter) {
        ShiroFilterFactoryBean sffb = new ShiroFilterFactoryBean();
        sffb.setSecurityManager(securityManager);
        //
        sffb.getFilters().put("logout", logoutFilter);
        //
        sffb.setLoginUrl("/a/shiro/login");
        sffb.setSuccessUrl("/a/shiro/restricted");
        sffb.setUnauthorizedUrl("/a/shiro/unauthorized");
        //
        sffb.setFilterChainDefinitionMap(ImmutableMap.<String, String>builder()
                .put("/a/shiro/login", "authc")
                .put("/a/shiro/logout", "logout")
                .put("/a/shiro/restricted", "user")
                .put("/a/shiro/privileged", "user, roles[special]")
                .build());
        //
        logger.debug(ObjectUtils.toString(sffb.getFilters()));
        //
        return sffb;
    }

    @DependsOn(value = {"lifecycleBeanPostProcessor"})
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

}
