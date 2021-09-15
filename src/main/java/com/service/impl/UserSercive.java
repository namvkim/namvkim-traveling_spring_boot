package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.component.UserConverter;
import com.dto.UserDTO;
import com.entity.UserEntity;
import com.repository.UserRepository;
import com.security.jwt.JwtProvider;
import com.service.IUserService;

@Service
public class UserSercive implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private JwtProvider tokenProvider;

	// lấy toàn bộ user
	@Override
	public List<UserDTO> get() {
		List<UserDTO> results = new ArrayList<UserDTO>();
		List<UserEntity> entities = userRepository.findAll();
		for (UserEntity item : entities) {
			UserDTO userDTO = userConverter.toDTO(item);
			results.add(userDTO);
		}
		return results;
	}

	// lấy 1 user khi login
	@Override
	public UserDTO getMyUser(HttpServletRequest request) {
		String jwt = getJwt(request);
		String username = tokenProvider.getUserNameFromJwtToken(jwt);
		UserEntity user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		UserDTO dto = userConverter.toDTO(user);
		return dto;
	}

	// lấy header và lọc ra jwt
	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}

		return null;
	}

}
