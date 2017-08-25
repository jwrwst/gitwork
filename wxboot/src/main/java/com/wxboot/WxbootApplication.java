package com.wxboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wxboot.*.**")
public class WxbootApplication {
	/**@SpringBootApplication注解默认扫描当前包及子包*/
	public static void main(String[] args) {
		SpringApplication.run(WxbootApplication.class, args);
	}
	
	
	
	
}
