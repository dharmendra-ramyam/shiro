package jhyun.ssw.spring.shiro;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by jhyun on 13. 12. 23.
 */
public class MyRealm extends AuthorizingRealm {

    public static Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO:
        return null;
    }


    private Map<String, String> userPasswords = ImmutableMap.<String, String>builder()
            .put("admin", "1234")
            .build();

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token instanceof UsernamePasswordToken) {
            UsernamePasswordToken upt = (UsernamePasswordToken) token;
            if (userPasswords.containsKey(upt.getUsername())
                    && StringUtils.equals(userPasswords.get(upt.getUsername()), new String(upt.getPassword()))) {
                // FIXME: hashed?
                SimpleAuthenticationInfo account = new SimpleAuthenticationInfo(upt.getPrincipal(), upt.getPassword(), this.getName());
                return account;
            }else {
                return null;
            }
        } else {
            return null;
        }
    }
}
