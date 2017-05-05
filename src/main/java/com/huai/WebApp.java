package com.huai;

import com.huai.util.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zhonghuai.zhang on 2017/4/13.
 */
@SpringBootApplication
@ImportResource("classpath:spring-config.xml")
@Import(value={SpringContextHolder.class})
public class WebApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }

}
