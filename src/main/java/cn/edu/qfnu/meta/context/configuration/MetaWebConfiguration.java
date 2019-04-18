package cn.edu.qfnu.meta.context.configuration;

import cn.edu.qfnu.meta.context.interceptor.MetaInterceptor;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Nemo Web配置器
 *
 * @author 王振琦
 * createAt: 2018/08/02
 * updateAt: 2018/08/03
 */
@Component
public class MetaWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MetaInterceptor())
                .addPathPatterns("/api/**")
                .addPathPatterns("")
                .addPathPatterns("/")
                .addPathPatterns("/index")
                .addPathPatterns("/user/**")
                .addPathPatterns("/author/**")
                .addPathPatterns("/book/**")
                .addPathPatterns("/admin/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("").setViewName("meta-index");
        registry.addViewController("/").setViewName("meta-index");
        registry.addViewController("/index").setViewName("meta-index");
        registry.addViewController("/courses").setViewName("meta-courses");
        registry.addViewController("/login").setViewName("user-login");
        registry.addViewController("/register").setViewName("user-register");
        registry.addViewController("/user/index").setViewName("user-index");
        registry.addViewController("/user/course").setViewName("user-course");
        registry.addViewController("/user/settings").setViewName("user-settings");
        registry.addViewController("/teacher/index").setViewName("teacher-index");
        registry.addViewController("/teacher/course").setViewName("teacher-course");
        registry.addViewController("/teacher/register").setViewName("teacher-register");
    }
}
