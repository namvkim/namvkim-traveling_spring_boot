package com.service;

import java.util.List;

import com.dto.NewDTO;

public interface INewService {
	NewDTO save(NewDTO newDTO);
	void delete(Long[] ids);
	List<NewDTO> get();
}
