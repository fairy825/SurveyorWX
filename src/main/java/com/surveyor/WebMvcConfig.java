package com.surveyor;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.surveyor.controller.interceptor.MiniInterceptor;


@SuppressWarnings("deprecation")
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
		.addResourceLocations("classpath:/META-INF/resources/");
//				.addResourceLocations("file:D:/eclipseProject/imooc-videos-dev/staticresource/");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(miniInterceptor()).addPathPatterns("/user/**");
//		.addPathPatterns("/bgm/**").addPathPatterns("/video/upload");
		super.addInterceptors(registry);
	}

	@Bean
	public MiniInterceptor miniInterceptor(){
		return new MiniInterceptor();
	}

}
