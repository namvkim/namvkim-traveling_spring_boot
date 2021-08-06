package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "destination")
public class DestinationEntity extends BaseEntity{

	@Column
	@NotNull(message = "Name cannot be null")  // ràng buộc và chú thích trên swagger2
	@ApiModelProperty(notes = "Name of destination") // chú thích trường trên model
	private String name;
	
	@OneToMany(mappedBy = "destination")
	private List<ImageEntity> images = new ArrayList<ImageEntity>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ImageEntity> getImages() {
		return images;
	}

	public void setImages(List<ImageEntity> images) {
		this.images = images;
	}
}
