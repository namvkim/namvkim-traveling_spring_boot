//package com.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//
//import io.swagger.annotations.ApiModelProperty;
//
//@Entity
//@Table(name = "image")
//public class ImageEntity extends BaseEntity{
//	
//	@Column
//	@NotNull(message = "Image cannot be null")  // ràng buộc và chú thích trên swagger2
//	@ApiModelProperty(notes = "Image of destination") // chú thích trường trên model
//	private String img;
//	
//	@Column
//	private String title;
//	
//	@Column
//	private String content;
//	
//	@Column
//	@NotNull(message = "type cannot be null")  // ràng buộc và chú thích trên swagger2
//	private String type;
//	
//	@ManyToOne
//	@JoinColumn(name = "destination_id")
//	private DestinationEntity destination;
//	
//	@OneToMany(mappedBy = "image")
//	private List<CommentEntity> comments = new ArrayList<CommentEntity>();
//
//	public String getImg() {
//		return img;
//	}
//
//	public void setImg(String img) {
//		this.img = img;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public DestinationEntity getDestination() {
//		return destination;
//	}
//
//	public void setDestination(DestinationEntity destination) {
//		this.destination = destination;
//	}
//
//	public List<CommentEntity> getComments() {
//		return comments;
//	}
//
//	public void setComments(List<CommentEntity> comments) {
//		this.comments = comments;
//	}
//}
