package com.wmanager.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wmanager.auth.config.TokenService;
import com.wmanager.auth.dto.AuthenticationDTO;
import com.wmanager.auth.dto.LoginResponseDTO;
import com.wmanager.auth.model.User;
import com.wmanager.auth.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	 @Autowired
	 private TokenService tokenService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		return ResponseEntity.ok(users);
	}

	
	@PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data){
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        Authentication auth = null;
        try {
            auth = this.authenticationManager.authenticate(usernamePassword);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

        String token = null;
        try {
            token = tokenService.generateToken((User) auth.getPrincipal());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
