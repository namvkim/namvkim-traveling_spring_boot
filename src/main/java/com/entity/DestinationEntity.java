package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private String address;
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@Column
	@NotNull(message = "img cannot be null")  // ràng buộc và chú thích trên swagger2
	private String img;
	
	@Column
	private Long likes;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity users;
	
	@OneToMany(mappedBy = "destination")
	private List<CommentEntity> comments = new ArrayList<CommentEntity>();

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public UserEntity getUsers() {
		return users;
	}

	public void setUsers(UserEntity users) {
		this.users = users;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}
}
