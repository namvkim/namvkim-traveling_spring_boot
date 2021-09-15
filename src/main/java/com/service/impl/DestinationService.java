package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.component.DestinationConverter;
import com.dto.DestinationDTO;
import com.entity.DestinationEntity;
import com.entity.UserEntity;
import com.repository.DestinationRepository;
import com.repository.UserRepository;
import com.security.jwt.JwtProvider;
import com.service.IDestinationService;

@Service
public class DestinationService implements IDestinationService {

	@Autowired
	private DestinationRepository destinationRepository;

	@Autowired
	private DestinationConverter destinationConverter;
	
	@Autowired
	private JwtProvider tokenProvider;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<DestinationDTO> get() {
		List<DestinationDTO> results = new ArrayList<DestinationDTO>();
		List<DestinationEntity> destinations = destinationRepository.findAll();
		for (DestinationEntity item : destinations) {
			DestinationDTO destinationDTO = destinationConverter.toDTO(item);
			results.add(destinationDTO);
		}
		return results;
	}

	@Override
	public DestinationDTO get(Long id) {
		DestinationEntity destination = destinationRepository.findOne(id);
		DestinationDTO result = destinationConverter.toDTO(destination);
		return result;
	}

	@Override
	public DestinationDTO save(HttpServletRequest request, DestinationDTO destinationDTO) {
		DestinationEntity destinationEntity = new DestinationEntity();
		if (destinationDTO.getId() != null) {
			DestinationEntity oldDestinationEntity = destinationRepository.findOne(destinationDTO.getId());
			destinationEntity = destinationConverter.toEntity(destinationDTO, oldDestinationEntity);
		} else {
			String jwt = getJwt(request);
			String username = tokenProvider.getUserNameFromJwtToken(jwt);
			UserEntity user = userRepository.findByUsername(username).orElseThrow(
					() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
			
			destinationEntity = destinationConverter.toEntity(user,destinationDTO);
		}
		destinationEntity = destinationRepository.save(destinationEntity);
		return destinationConverter.toDTO(destinationEntity);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long item : ids) {
			destinationRepository.delete(item);
		}
	}

	@Override
	public List<DestinationDTO> getMyDestination(HttpServletRequest request) {
		List<DestinationDTO> results = new ArrayList<DestinationDTO>();

		String jwt = getJwt(request);
		String username = tokenProvider.getUserNameFromJwtToken(jwt);
		UserEntity user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		List<DestinationEntity> destinations = destinationRepository.findByUsers_id(user.getId());
		for (DestinationEntity item : destinations) {
			DestinationDTO destinationDTO = destinationConverter.toDTO(item);
			results.add(destinationDTO);
		}
		
		return results;
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
