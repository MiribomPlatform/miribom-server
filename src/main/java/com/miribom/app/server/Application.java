/*
 * @(#)Application.java 2020. 09. 21
 */
package com.miribom.app.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author changwoo.son
 */
@SpringBootApplication(scanBasePackages = {"com.miribom.app.server"})
public class Application {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
