package com.jwt.kranthi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.jwt.kranthi.security.CustomUserDetailService;

@Configuration
public class AppConfig {
	
//	@Autowired
//	CustomUserDetailService customUserDetailService;
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =  User.builder().username("Harsh").password(passwordEncoder().encode("abc")).roles("ADMIN").build();
//		UserDetails user1 = User.builder().username("durgesh").password(passwordEncoder().encode("abc")).roles("ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(user, user1);
//	}
	
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		return customUserDetailService;
//	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
