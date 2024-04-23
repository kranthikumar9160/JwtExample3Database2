package com.jwt.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtExample3DatabaseApplication implements CommandLineRunner {


	
	public static void main(String[] args) {
		SpringApplication.run(JwtExample3DatabaseApplication.class, args);
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(passwordEncoder().encode("abc"));
//	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Project started");
	}
	
	

}
