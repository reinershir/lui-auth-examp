package io.github.reiner.examp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.reinershir.auth.annotation.EnableAuthentication;

@SpringBootApplication
@MapperScan(value = "io.github.reiner.examp.mapper")
@EnableAuthentication
public class Application {
 
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
