package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity{

	@Column
	@NotNull(message = "Image cannot be null")  // ràng buộc và chú thích trên swagger2
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "image_id")
	private ImageEntity image;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity users;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ImageEntity getImage() {
		return image;
	}

	public void setImage(ImageEntity image) {
		this.image = image;
	}

	public UserEntity getUsers() {
		return users;
	}

	public void setUsers(UserEntity users) {
		this.users = users;
	}
}
