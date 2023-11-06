package com.wmanager.auth.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wmanager.auth.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
@Configuration
public class SecurityFilter extends OncePerRequestFilter {
	
	/*
	 * private String HEADER_STRING = "Authorization";
	 * 
	 * private String TOKEN_PREFIX = "Bearer ";
	 */
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var token = this.recoverToken(request);
		
		if(token != null) {
			try {
				var login = tokenService.validateToken(token);
				UserDetails userDetails = userRepository.findByEmail(login);
				var authentication = UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		filterChain.doFilter(request, response);
		
	}
	
	
	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
	}

}
