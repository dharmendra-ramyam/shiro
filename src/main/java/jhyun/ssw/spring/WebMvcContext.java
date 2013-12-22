package jhyun.ssw.spring;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by jhyun on 13. 12. 23.
 */
@ComponentScan(basePackages = {"jhyun.ssw.webmvc"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@Configuration
public class WebMvcContext {

    @Bean
    public BeanNameViewResolver beanNameViewResolver() {
        BeanNameViewResolver bnvr = new BeanNameViewResolver();
        bnvr.setOrder(1);
        return bnvr;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver irvr = new InternalResourceViewResolver();
        irvr.setPrefix("/WEB-INF/jsp/views/");
        irvr.setSuffix(".jsp");
        // NOTE: 이렇게 하면, appliction-context의 bean에 바로 이름으로 jsp-el에서 접근이 가능.
        irvr.setExposeContextBeansAsAttributes(true);
        return irvr;
    }
}
