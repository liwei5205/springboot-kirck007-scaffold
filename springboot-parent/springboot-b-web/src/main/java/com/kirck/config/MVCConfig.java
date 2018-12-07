package com.kirck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kirck.inter.AllInterceptor;


@Configuration
public class MVCConfig implements WebMvcConfigurer {
	
		@Bean
		public AllInterceptor getAllInterceptor() {
			return  new AllInterceptor();
		}
	
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(getAllInterceptor());
	    }
}
