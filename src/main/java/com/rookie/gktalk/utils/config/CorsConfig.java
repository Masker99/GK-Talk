package com.rookie.gktalk.utils.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")//允许跨域的访问路径
                    .allowedOrigins("http://localhost:8081")//允许跨域访问的源
                    .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")//允许请求方法
                    .allowCredentials(true)//是否允许发送cookie
                    .maxAge(3600)//预检的间隔时间
                    .allowedHeaders("*");//允许跨域访问的header
    }
}
