package com.vti.exam;

import com.vti.exam.entity.Account;
import com.vti.exam.form.AccountCreateForm;
import org.springframework.boot.SpringApplication;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
//						.allowedOrigins("http://127.0.0.1:5500")
						.allowedOrigins("http://localhost:3000/")
						.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "DELETE")
						.allowCredentials(true)
						.exposedHeaders(HttpHeaders.AUTHORIZATION);
			}
		};
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.typeMap(AccountCreateForm.class, Account.class).addMappings(mapping -> mapping.skip(Account::setId));
		return mapper;
	}
}
