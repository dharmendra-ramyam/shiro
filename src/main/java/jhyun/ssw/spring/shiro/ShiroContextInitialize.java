package jhyun.ssw.spring.shiro;

import com.google.common.base.Preconditions;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jhyun on 13. 12. 21.
 */
@Configuration
public class ShiroContextInitialize implements InitializingBean{

    @Autowired
    private org.apache.shiro.mgt.SecurityManager securityManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(securityManager);
        SecurityUtils.setSecurityManager(securityManager);
    }
}
