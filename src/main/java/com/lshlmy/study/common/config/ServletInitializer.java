package com.lshlmy.study.common.config;

import javax.servlet.*;

import com.lshlmy.study.common.el.EscapeXmlELResolverListener;
import com.lshlmy.study.common.filter.HttpRequestFilter;
import com.lshlmy.study.common.util.SystemEnvironmentUtil;
import com.lshlmy.study.controller.TestServlet;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
import org.springframework.web.util.IntrospectorCleanupListener;


/**
 * @description 程序启动
 * @author lshlmy
 */
public class ServletInitializer extends AbstractDispatcherServletInitializer {

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.scan(ClassUtils.getPackageName(getClass()));
		return context;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		return null;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);

		DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain");
		filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
		servletContext.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");

		//EL
		servletContext.addListener(EscapeXmlELResolverListener.class);

		//Spring
		servletContext.setInitParameter("contextConfigLocation", "classpath:spring.xml");
		servletContext.addListener(ContextLoaderListener.class);
		servletContext.addListener(IntrospectorCleanupListener.class);

		//日志

	}
	@Override
	protected Filter[] getServletFilters(){
		return new Filter[] {new HttpRequestFilter()};
	}

}
