package com.javahash.spring.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);
    // AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    // ctx.register(Config.class);
    // ctx.setServletContext(servletContext);
    //
    // Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
    // servlet.addMapping("/");
    // servlet.setLoadOnStartup(1);
		System.out.println("spring 上下文初始化完成......");
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {Config.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }

	@Override
	protected Filter[] getServletFilters() {
		Filter[] filters = new Filter[1];
		CharacterEncodingFilter characterFilter = new CharacterEncodingFilter();
		characterFilter.setForceEncoding(true);
		characterFilter.setEncoding("UTF-8");
		filters[0] = characterFilter;
		return filters;
	}
}
