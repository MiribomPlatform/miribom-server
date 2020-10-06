/*
 * @(#)SwaggerConfig.java 2020. 09. 30
 */
package com.miribom.app.server.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author changwoo.son
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.miribom.app.server.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				.produces(getProduces())
				.useDefaultResponseMessages(true);
	}


	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Miribom Server API").description("미리봄 서버 apis").build();
	}

	private Set<String> getProduces() {
		Set<String> produces = new HashSet<>();
		produces.add(MediaType.APPLICATION_JSON_VALUE);
		return produces;
	}
}
