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
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable());
		
		http.authorizeHttpRequests(authorize ->
			authorize
			.requestMatchers("/api/greeting").hasRole("USER") //Only ROLE_USER can access
			.requestMatchers("/api/hello").hasRole("ADMIN") //Only ROLE_ADMIN can access
			.anyRequest().authenticated()
				); //Any other request must be authenticated
		http.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails user1 = User.withUsername("ramesh").password(passwordEncoder().encode("rameshPassword")).roles("USER").build();
		UserDetails user2 = User.withUsername("suresh").password(passwordEncoder().encode("sureshPassword")).roles("USER").build();
		UserDetails admin = User.withUsername("mahesh").password(passwordEncoder().encode("maheshPassword")).roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user1, user2, admin);
	}
}
