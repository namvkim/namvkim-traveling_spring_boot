package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDTO;
import com.service.impl.UserSercive;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserApi {
	
	@Autowired
	private UserSercive userSercive;
	
	@GetMapping(value = "/user")
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserDTO> getUser() {
		return userSercive.get();
	}
	
	@GetMapping(value = "/user/myuser")
	@PreAuthorize("hasRole('USER')")
	public UserDTO getMyUser(HttpServletRequest request) {
		return userSercive.getMyUser(request);
	}
}
