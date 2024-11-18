package com.j_norrman.project_javaee.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("forward:/index.html");
    registry.addViewController("/login").setViewName("forward:/login.html");
    registry.addViewController("/logout").setViewName("forward:/index.html");

}
}
