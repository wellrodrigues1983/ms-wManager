package com.wmanager.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wmanager.auth.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

}
