package com.kirck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kirck.springboot-mapper.*")
public class BWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(BWebApplication.class, args);
	}
}
