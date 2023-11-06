package com.wmanager.auth.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wmanager.auth.config.TokenService;
import com.wmanager.auth.dto.AuthenticationDTO;
import com.wmanager.auth.dto.LoginResponseDTO;
import com.wmanager.auth.dto.UserDTO;
import com.wmanager.auth.exceptions.GlobalExceptionHandler;
import com.wmanager.auth.model.UserModel;
import com.wmanager.auth.repository.UserRepository;
import com.wmanager.auth.util.DateUtil;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private PasswordEncoder encoder;

	public ResponseEntity<LoginResponseDTO> getLogin(AuthenticationDTO data) {		
		
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		Authentication auth = null;
		try {
			auth = this.authenticationManager.authenticate(usernamePassword);
		} catch (AuthenticationException e) {
			GlobalExceptionHandler.sendMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.LOCKED).build();			
		}

		String token = null;
		try {
			token = tokenService.generateToken((UserModel) auth.getPrincipal());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		if(token != null) {
			return ResponseEntity.ok().header("Authorization", "Bearer " + token).build();
		}else {
			return ResponseEntity.badRequest().build();
		}
				
	}
		

	public ResponseEntity<List<UserModel>> getAllUsers() {
		List<UserModel> users = userRepository.findAll();
		return ResponseEntity.ok(users);
	}


	public ResponseEntity<UserModel> createUser(UserModel userModel) {
		UserModel newUser = new UserModel();
		try {
			newUser.setName(userModel.getName());
			newUser.setEmail(userModel.getEmail());
			newUser.setPassword(encoder.encode(userModel.getPassword()));
			newUser.setRole(userModel.getRole());
			newUser.setCreatedAt(DateUtil.formatarData(new Date()));
			newUser.setActivate(userModel.getActivate());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ofNullable(newUser);			
		}
		
		try {
			userRepository.save(newUser);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().build();			
		}
		
		return ResponseEntity.ok().build();
	}


	public ResponseEntity<UserDTO> updateUser(UserDTO dto, Long id) {
		UserModel upUser = new UserModel();
		try {
			upUser.setId(id);
			upUser.setName(dto.name());
			upUser.setEmail(dto.email());
			upUser.setPassword(encoder.encode(dto.password()));
			upUser.setRole(dto.role());
			upUser.setActivate(dto.activate());
			upUser.setUpdatedAt(DateUtil.formatarData(new Date()));
			
			userRepository.save(upUser);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
}
