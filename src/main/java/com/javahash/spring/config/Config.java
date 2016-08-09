package com.javahash.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.javahash.spring.interceptor.DataSourceChangeInterceptor;

@Configuration
// Marks this class as configuration
// Specifies which package to scan
@ComponentScan({"com.javahash.spring.*"})
// Enables Spring's annotations
@EnableWebMvc
// 17.15.1 Enabling the MVC Java Config or the MVC XML Namespace
// To enable MVC Java config add the annotation @EnableWebMvc to one of your
// @Configuration classes:
//@Component
// 基于注解的配置和xml配置混合使用
// @ImportResource(value = { "classpath:jms-config.xml" })
public class Config extends WebMvcConfigurerAdapter {

  @Bean
  public UrlBasedViewResolver setupViewResolver() {
    UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    resolver.setViewClass(JstlView.class);
    return resolver;
  }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(new Integer("3600"));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DataSourceChangeInterceptor()).addPathPatterns("/**").excludePathPatterns("/test/**");
	}
}
