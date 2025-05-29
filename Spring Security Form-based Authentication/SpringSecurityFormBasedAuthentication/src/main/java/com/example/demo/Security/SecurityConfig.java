package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable());
		
		http.authorizeHttpRequests(authorizeRequests -> 
			authorizeRequests.requestMatchers("/").permitAll().anyRequest().authenticated());
		
		http.formLogin(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.withUsername("user1").password(passwordEncoder().encode("password1")).roles("USER").build();
		
		UserDetails admin1 = User.withUsername("admin1").password(passwordEncoder().encode("adminPassword1")).roles("ADMIN").build();
		
		UserDetails seller1 = User.withUsername("seller1").password(passwordEncoder().encode("sellerPassword")).roles("SELLER").build();
		
		return new InMemoryUserDetailsManager(user1, admin1, seller1);
	}
}
