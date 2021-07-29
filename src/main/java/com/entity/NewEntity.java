package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "new")
public class NewEntity extends BaseEntity {
	@Column(name = "title")
	@NotNull(message = "Title cannot be null")  // ràng buộc và chú thích trên swagger2
	@ApiModelProperty(notes = "The database generated new ID") // chú thích trường trên model
	private String title;
	
	@Column(name = "thumbnail")
	@ApiModelProperty(notes = "Thumbnail to select picture") // chú thích trường trên model
	private String thumbnail;
	
//	@Max(value = 65, message = "Shortdescription should not be greater than 200 letter")  // ràng buộc và chú thích trên swagger2
	@ApiModelProperty(notes = "Shortdescription to introduce some infomation of news") // chú thích trường trên model
	@Column(name = "shortdescription")
	private String shortDescription;
	
	@Column(name = "content")
	@ApiModelProperty(notes = "Content to show all infomation") // chú thích trường trên model
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@ApiModelProperty(notes = "Category_id to reference category table") // chú thích trường trên model
	private CategoryEntity category;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
}
