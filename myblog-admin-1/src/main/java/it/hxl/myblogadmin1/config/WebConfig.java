package it.hxl.myblogadmin1.config;

import it.hxl.myblogadmin1.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/article").setViewName("article");
        registry.addViewController("/category").setViewName("category");
//        registry.addViewController("/comment").setViewName("comment");
        registry.addViewController("/flink").setViewName("flink");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/manage-user").setViewName("manage-user");
        registry.addViewController("/loginlog").setViewName("loginlog");
//        registry.addViewController("/diary").setViewName("diary");
        registry.addViewController("/readset").setViewName("readset");
        registry.addViewController("/setting").setViewName("setting");
        registry.addViewController("/update-category").setViewName("update-category");
        registry.addViewController("/update-flink").setViewName("update-flink");
        registry.addViewController("/update-article").setViewName("update-article");
        registry.addViewController("/add-article").setViewName("add-article");
        registry.addViewController("/add-category").setViewName("add-category");
        registry.addViewController("/add-flink").setViewName("add-flink");
        registry.addViewController("/add-diary").setViewName("add-diary");
    }

    @Autowired
    private CommonInterceptor commonInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private IndexInterceptor indexInterceptor;
    @Autowired
    private AddArticleInterceptor addArticleInterceptor;
    @Autowired
    private CategoryInterceptor categoryInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor);
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/login", "/adminLogin","/bootstrap/**", "/error/**", "/images/**", "/js/**");
        registry.addInterceptor(indexInterceptor).addPathPatterns("/index", "/adminLogin");
        registry.addInterceptor(addArticleInterceptor).addPathPatterns("/add-article");
        registry.addInterceptor(categoryInterceptor).addPathPatterns("/category");
    }

}
