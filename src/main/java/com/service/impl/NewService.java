package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.component.NewConverter;
import com.dto.NewDTO;
import com.entity.CategoryEntity;
import com.entity.NewEntity;
import com.repository.CategoryRepository;
import com.repository.NewRepository;
import com.service.INewService;

@Service
public class NewService implements INewService{
	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NewConverter newConverter;

	@Override
	public List<NewDTO> get() {
		List<NewDTO> results=new ArrayList<NewDTO>();
		List<NewEntity> entities = newRepository.findAll();
		for(NewEntity item: entities) {
			NewDTO newDTO = newConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}
	
	@Override
	public NewDTO save(NewDTO newDTO) {
		NewEntity newEntity = new NewEntity();
		if (newDTO.getId() != null) {
			NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
			newEntity = newConverter.toEntity(newDTO, oldNewEntity);
		} else {
			newEntity = newConverter.toEntity(newDTO);
		}
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		newEntity.setCategory(categoryEntity);
		newEntity = newRepository.save(newEntity);
		return newConverter.toDTO(newEntity);
	}

	@Override
	public void delete(Long[] ids) {
		for(Long item: ids) {
			newRepository.delete(item);
		}
	}
}
