package jhyun.ssw.spring;

import jhyun.ssw.spring.shiro.ShiroSessionHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * Created by jhyun on 13. 12. 23.
 */
@ComponentScan(basePackages = {"jhyun.ssw.webmvc"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@Configuration
public class WebMvcContext extends WebMvcConfigurerAdapter {

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

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ShiroSessionHandlerMethodArgumentResolver());
    }
}
