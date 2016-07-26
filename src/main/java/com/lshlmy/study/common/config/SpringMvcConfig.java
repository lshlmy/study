package com.lshlmy.study.common.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.lshlmy.study.common.config.mvc.JsonViewResolver;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;


/**
 * @description Spring Mvc 注解配置
 * @author lshlmy
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.lshlmy.study.controller")
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(false).defaultContentType(MediaType.valueOf("text/html; charset=UTF-8"));
	}

	/** 数据类型转换 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(stringHttpMessageConverter());
		converters.add(new ResourceHttpMessageConverter());
		converters.add(new SourceHttpMessageConverter<>());
		converters.add(new AllEncompassingFormHttpMessageConverter());
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
		
		
	}

	/** 视图解析 器 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(jsonViewResolver());
		resolvers.add(jspViewResolver());
		resolver.setViewResolvers(resolvers);

		return resolver;
	}

	/** JSP视图 */
	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/** JSON视图 */
	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

	/** JSON数据转换 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).modulesToInstall(new ParameterNamesModule());
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
		converter.setObjectMapper(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.TEXT_HTML);
		mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		converter.setSupportedMediaTypes(mediaTypes);
		return converter;
	}

	/** String数据转换 */
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter messageConverter = new StringHttpMessageConverter(Charset.forName(CharEncoding.UTF_8));
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.TEXT_PLAIN);
		mediaTypes.add(MediaType.TEXT_HTML);
		messageConverter.setSupportedMediaTypes(mediaTypes);
		messageConverter.setWriteAcceptCharset(false);
		return messageConverter;
	}

	/** spring mvc 访问静态资源 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
				.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
	}

	/** 用户身份参数，可以使用注解方式获取用户 */
	@Bean
	public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {
		return new AuthenticationPrincipalArgumentResolver();
	}

	/** 方法参数处理 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(authenticationPrincipalArgumentResolver());
	}

	/** 文件上传 */
	@Bean
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(100000);
		commonsMultipartResolver.setMaxInMemorySize(40960);
		commonsMultipartResolver.setDefaultEncoding(CharEncoding.UTF_8);
		return new CommonsMultipartResolver();
	}
}
