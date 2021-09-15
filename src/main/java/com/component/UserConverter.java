package com.component;

import org.springframework.stereotype.Component;

import com.dto.UserDTO;
import com.entity.UserEntity;

@Component
public class UserConverter {
	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setName(entity.getName());
		dto.setUsername(entity.getUsername());
		dto.setPassword(entity.getPassword());
		dto.setPhone(entity.getPhone());
		dto.setAddress(entity.getAddress());
		
		return dto;
	}
}
