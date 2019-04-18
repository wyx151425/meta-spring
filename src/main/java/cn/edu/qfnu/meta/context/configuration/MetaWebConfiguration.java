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
        registry.addViewController("").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/user/index").setViewName("user-blog");
        registry.addViewController("/user/course").setViewName("user-book");
        registry.addViewController("/user/lesson").setViewName("user-page");
        registry.addViewController("/user/settings").setViewName("user-settings");
        registry.addViewController("/lecturer/list").setViewName("author-list");
        registry.addViewController("/lecturer/index").setViewName("author-blog");
        registry.addViewController("/lecturer/course").setViewName("author-book");
        registry.addViewController("/course/list").setViewName("book-list");
        registry.addViewController("/teacher/register").setViewName("author-register");
        registry.addViewController("/admin/index").setViewName("admin-index");
    }
}
