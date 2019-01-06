package com.kirck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages="com.kirck")
@MapperScan("com.kirck.springboot-mapper.*")
@EnableScheduling
public class BWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(BWebApplication.class, args);
	}
}
