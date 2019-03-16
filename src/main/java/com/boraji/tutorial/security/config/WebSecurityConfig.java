package com.boraji.tutorial.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.inMemoryAuthentication()
      .withUser("admin").password("").roles("USER");
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.cors()
      	.and()
      	.authorizeRequests().antMatchers("/").hasRole("USER")
      	.and()
      	.httpBasic();
   }
   
   public void configure(WebSecurity web) throws Exception {
       web
               .ignoring()
               .antMatchers("/static/**");
   }
}
