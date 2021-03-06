package com.kirck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages="com.kirck")
@MapperScan("com.kirck.springboot-mapper.*")
public class BWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(BWebApplication.class, args);
	}
}
