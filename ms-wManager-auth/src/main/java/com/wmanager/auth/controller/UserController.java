package com.wmanager.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wmanager.auth.dto.AuthenticationDTO;
import com.wmanager.auth.dto.LoginResponseDTO;
import com.wmanager.auth.dto.UserDTO;
import com.wmanager.auth.model.UserModel;
import com.wmanager.auth.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("/users")
	public ResponseEntity<List<UserModel>> getAllUsers() {
		return service.getAllUsers();
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
		return service.getLogin(data);
	}

	@PostMapping("/create")
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel){
		return service.createUser(userModel);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO dto){ 
	 return service.updateUser(dto, id);
	}
	 
}
