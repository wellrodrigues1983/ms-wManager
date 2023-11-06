package com.wmanager.auth.dto;

import java.util.Date;

import com.wmanager.auth.enumerator.UserRole;

public record UserDTO(Long id, String name, String password, String email, Boolean activate, UserRole role, Date updateAt) {

}
