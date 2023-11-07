package com.wmanager.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private SecurityFilter securityFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/login/**").permitAll()
								.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated())
				.headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	/*
	 * @Bean public UserDetailsService userDetailsService() {
	 * 
	 * UserDetails userDetails = User.withUsername("admin@admin.com")
	 * .password(passwordEncoder().encode("123")) .roles("ADMIN") .build();
	 * 
	 * return new InMemoryUserDetailsManager(userDetails); }
	 */

}
