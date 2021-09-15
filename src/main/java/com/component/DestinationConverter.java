package com.component;

import org.springframework.stereotype.Component;

import com.dto.DestinationDTO;
import com.entity.DestinationEntity;
import com.entity.UserEntity;

@Component
public class DestinationConverter {
	public DestinationEntity toEntity(UserEntity user, DestinationDTO dto) {
		DestinationEntity entity = new DestinationEntity();
		entity.setAddress(dto.getAddress());
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setImg(dto.getImg());
		entity.setLikes(0L);
		entity.setUsers(user);
		
		return entity;
	}
	
	public DestinationDTO toDTO(DestinationEntity entity) {
		DestinationDTO dto = new DestinationDTO();
		if(entity.getId()!=null) {
			dto.setId(entity.getId());
		}
		dto.setAddress(entity.getAddress());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setImg(entity.getImg());
		dto.setLikes(entity.getLikes());
		
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public DestinationEntity toEntity(DestinationDTO dto, DestinationEntity entity) {
		entity.setAddress(dto.getAddress());
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setImg(dto.getImg());
		entity.setLikes(dto.getLikes());
		return entity;
	}
}
