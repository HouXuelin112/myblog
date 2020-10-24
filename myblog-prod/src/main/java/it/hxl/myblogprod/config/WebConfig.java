package it.hxl.myblogprod.config;

import it.hxl.myblogprod.converter.DateConverter;
import it.hxl.myblogprod.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.PostConstruct;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ServerEndpointExporter serverEndpointExporter()
    {
        return new ServerEndpointExporter();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 解决跨域
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowCredentials(true)
            .allowedMethods("GET", "POST", "DELETE", "PUT")
            .maxAge(3600);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/article").setViewName("article");

        registry.addViewController("/commentsList").setViewName("comments");

        registry.addViewController("/diary").setViewName("diary");

        registry.addViewController("/index").setViewName("index");

        registry.addViewController("/link").setViewName("link");
        registry.addViewController("/toLoginPage").setViewName("login");
        registry.addViewController("/login").setViewName("login");

        registry.addViewController("/message").setViewName("message");

        registry.addViewController("/read").setViewName("read");

        registry.addViewController("/toShowUsers").setViewName("showUsers");
        registry.addViewController("/toSelect").setViewName("select");

    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//    }

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    @PostConstruct //初始化全局转换器
    public void initializer(){
//        System.out.println("ddd");
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null){
            GenericConversionService conversionService = (GenericConversionService) initializer.getConversionService();
//            System.out.println("aaa");
            conversionService.addConverter(new DateConverter());
        }
    }
    @Autowired
    private IndexInterceptor indexInterceptor;
    @Autowired
    private BlogInterceptor blogInterceptor;
    @Autowired
    private CommonInterceptor commonInterceptor;
    @Autowired
    private CommentsInterceptor commentsInterceptor;
    @Autowired
    private MessageInterceptor messageInterceptor;
    @Autowired
    private DiaryInterceptor diaryInterceptor;
    @Autowired
    private LinkInterceptor linkInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration commonIr = registry.addInterceptor(commonInterceptor);
        commonIr.addPathPatterns("/index", "/article", "/article/*", "/message", "/read", "/read/*", "/", "/diary", "/about", "/link", "/commentsList");

        InterceptorRegistration indexIr = registry.addInterceptor(indexInterceptor);
        indexIr.addPathPatterns("/index", "/");

        InterceptorRegistration blogIr = registry.addInterceptor(blogInterceptor);
        blogIr.addPathPatterns("/article");
        blogIr.addPathPatterns("/article/*");

        InterceptorRegistration commentsIr = registry.addInterceptor(commentsInterceptor);
        commentsIr.addPathPatterns("/commentsList");

        InterceptorRegistration messageIr = registry.addInterceptor(messageInterceptor);
        messageIr.addPathPatterns("/message");

        InterceptorRegistration diaryIr = registry.addInterceptor(diaryInterceptor);
        diaryIr.addPathPatterns("/diary");

        InterceptorRegistration linkIr = registry.addInterceptor(linkInterceptor);
        linkIr.addPathPatterns("/link");

    }
}
