package com.javahash.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Configuration
// Marks this class as configuration
// Specifies which package to scan
@ComponentScan({"com.javahash.spring"})
// Enables Spring's annotations
// @EnableWebMvc
//@Component
public class DaoConfig {

  @Bean
  public DriverManagerDataSource setUpDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://192.168.20.201:3306/ngoms0818");
    dataSource.setUsername("root");
    dataSource.setPassword("root123");
    return dataSource;
  }

  // @Bean
  // public UrlBasedViewResolver setupViewResolver() {
  // UrlBasedViewResolver resolver = new UrlBasedViewResolver();
  // resolver.setPrefix("/WEB-INF/views/");
  // resolver.setSuffix(".jsp");
  // resolver.setViewClass(JstlView.class);
  // return resolver;
  // }

}
