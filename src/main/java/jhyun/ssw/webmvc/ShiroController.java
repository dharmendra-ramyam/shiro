package jhyun.ssw.webmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jhyun on 13. 12. 23.
 */
@RequestMapping(value = "/shiro")
@Controller
public class ShiroController {

    @RequestMapping(value = "/login")
    public ModelAndView login(ModelMap m) {
        return new ModelAndView("shiro/login", m);
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(ModelMap m) {
        return new ModelAndView("shiro/index", m);
    }

    @RequestMapping(value = "/restricted")
    public ModelAndView restricted(ModelMap m) {
        return new ModelAndView("shiro/restricted", m);
    }

    @RequestMapping(value = "/privileged")
    public ModelAndView privileged(ModelMap m) {
        return new ModelAndView("shiro/privileged", m);
    }

    @RequestMapping(value = "/unauthorized")
    public ModelAndView unauthorized(ModelMap m) {
        return new ModelAndView("shiro/unauthorized", m);
    }
}
