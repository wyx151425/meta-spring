package cn.edu.qfnu.meta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 服务启动类
 *
 * @author 王振琦
 * createAt: 2018/08/01
 * updateAt: 2018/08/13
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.edu.qfnu.meta.repository")
public class MetaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MetaApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
