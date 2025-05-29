package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authentiationManager(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers("/api/**").permitAll().anyRequest().authenticated());
		http.formLogin(form -> form
				.loginPage("/api/login")
				.loginProcessingUrl("/api/login")
				.defaultSuccessUrl("/api/welcome", true)
				.permitAll()
				).logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
						.logoutSuccessUrl("/api/login?logout")
						.permitAll()
						);
		//http.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
}
