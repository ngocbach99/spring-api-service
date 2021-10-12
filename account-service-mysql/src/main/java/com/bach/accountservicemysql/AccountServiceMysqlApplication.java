package com.bach.accountservicemysql;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConfigurationCondition.ConfigurationPhase;
import org.springframework.ui.ModelMap;

@SpringBootApplication
public class AccountServiceMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceMysqlApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)//Map cả cho thuộc tính private
				.setMatchingStrategy(MatchingStrategies.STRICT); //Map đúng tên với nhau
		
		return modelMapper;
	}
}
