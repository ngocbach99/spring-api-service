package com.bach.springsecurityjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecurityJwtApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
		
		BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
		String password = "123456";
		String endcodedPassword = passEncode.encode(password);
		
		System.out.println(endcodedPassword);
		
	}
}
