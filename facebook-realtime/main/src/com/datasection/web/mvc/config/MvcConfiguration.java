package com.datasection.web.mvc.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = "com.datasection.facebook.mvc")
@EnableWebMvc
@Import(value = { SecurityConfig.class, BoneCPConfig.class, ProjectConfig.class })
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

//	@Bean
//	public ViewResolver viewResolver() {
//		// ClassLoaderTemplateResolver templateResolver = new
//		// ClassLoaderTemplateResolver();
//		// templateResolver.setTemplateMode("XHTML");
//		// templateResolver.setPrefix("template/");
//		// templateResolver.setSuffix(".html");
//
//		ServletContextTemplateResolver tplr = new ServletContextTemplateResolver();
//		tplr.setPrefix("/WEB-INF/html/");
//		tplr.setSuffix(".html");
//		// TODO disable cached thymeleaf so that any modification to template
//		// should be updated immediately. However, the line should be removed in
//		// production mode
//		tplr.setCacheable(false);
//		tplr.setTemplateMode("XHTML");
//		tplr.setCharacterEncoding("UTF-8");
//
//		SpringTemplateEngine engine = new SpringTemplateEngine();
//		engine.setTemplateResolver(tplr);
//
//		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//		viewResolver.setTemplateEngine(engine);
//		viewResolver.setCharacterEncoding("UTF-8");
//		return viewResolver;
//
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/data/**").addResourceLocations("/data/");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(responseBodyConverter());
		MappingJackson2HttpMessageConverter cv = new MappingJackson2HttpMessageConverter();
		converters.add(cv);
	}

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
		return converter;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor());
		// registry.addInterceptor(new
		// TransactionInterceptor()).addPathPatterns("/person/save/*");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).favorParameter(true);
	}
}
