package com.webapp.TextBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebMvc
public class PathConfig implements WebMvcConfigurer {
    @Autowired
    private ApplicationContext applicationContext;
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"
    };

    private static final String CLASSPATH_IMG_RESOURCE_LOCATION = "classpath:/static/img/";
    private static final String CLASSPATH_JS_RESOURCE_LOCATION = "classpath:/static/JS/";

    private static final String CLASSPATH_CSS_RESOURCE_LOCATION = "classpath:/static/CSS/";

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/"); // demo/src/main
        bean.setSuffix(".jsp");
        bean.setOrder(0); //set to 1 if you want to use thymeleaf as initial view in controller
        return bean;
    }
    @Bean
    public WebMvcConfigurer forwardToIndex() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // forward requests to /admin and /user to their index.html
                registry.addViewController("/").setViewName(
                        "forward:/WEB-INF/jsp/index.jsp");
//                registry.addViewController("/addSucess").setViewName(
//                        "forward:/WEB-INF/jsp/addSucess.jsp");
//                registry.addViewController("/errpage").setViewName(
//                        "forward:/WEB-INF/jsp/errpage.jsp");
//                registry.addViewController("/students").setViewName(
//                        "forward:/WEB-INF/jsp/students.jsp");
//                registry.addViewController("/error").setViewName(
//                        "forward:/WEB-INF/jsp/404.jsp");
//                registry.addViewController("/notfound").setViewName(
//                        "forward:/WEB-INF/jsp/notfound.jsp");

            }
        };




    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/img/**")
//                .addResourceLocations("/static/img/");
//
//        registry.addResourceHandler("/css/**")
//                .addResourceLocations("/static/css/");
//
//        registry.addResourceHandler("/js/**")
//                .addResourceLocations("/WEB-INF/js/");
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

    }
}
