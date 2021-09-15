package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dto.DestinationDTO;

public interface IDestinationService {
	DestinationDTO save(HttpServletRequest request,DestinationDTO newDTO);
	void delete(Long[] ids);
	List<DestinationDTO> get();
	DestinationDTO get(Long id);
	List<DestinationDTO> getMyDestination(HttpServletRequest request);
}
