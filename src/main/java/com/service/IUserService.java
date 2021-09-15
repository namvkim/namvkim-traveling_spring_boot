package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dto.UserDTO;

public interface IUserService {
	List<UserDTO> get();
	UserDTO getMyUser(HttpServletRequest request);
}
