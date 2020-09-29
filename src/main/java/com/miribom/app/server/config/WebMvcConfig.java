/*
 * @(#)WebMvcConfig.java 2020. 09. 30
 *
 * Copyright 2020 WorksMobile Corp. All rights Reserved.
 * Works Mobile PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.miribom.app.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author changwoo.son
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// swagger 적용을 위한 설정
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	}
}
