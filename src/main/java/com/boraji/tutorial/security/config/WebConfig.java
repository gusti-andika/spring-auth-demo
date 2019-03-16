package com.boraji.tutorial.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.boraji.tutorial.spring.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {
   @Override
   public void configureViewResolvers(ViewResolverRegistry registry) {
      registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
   }
   
   @Override
   public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/getString.json").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
               .allowedHeaders("*");
   }
   
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry
               .addResourceHandler("/static/**")
               .addResourceLocations("/static/");
   }
}
