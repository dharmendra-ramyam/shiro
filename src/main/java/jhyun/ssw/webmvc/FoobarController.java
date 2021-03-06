package jhyun.ssw.webmvc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.session.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jhyun on 13. 12. 23.
 */
@RequestMapping(value = "/foobar")
@Controller
public class FoobarController {

    @RequestMapping(value = "/hello")
    public ModelAndView hello(ModelMap m) {
        return new ModelAndView("foobar/hello", m);
    }

    @RequiresUser
    @RequestMapping(value = "/restrictedHello")
    public ModelAndView restrictedHello(ModelMap m, Session session) {
        m.put("s", session);
        return new ModelAndView("foobar/hello", m);
    }
}
