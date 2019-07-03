package com.ep.solrtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan("com.ep.solrtest.mapper")
public class SolrtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolrtestApplication.class, args);
	}

}
