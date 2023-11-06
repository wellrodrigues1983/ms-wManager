package com.wmanager.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.wmanager.auth.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
	
	UserDetails findByEmail(String email);

}
