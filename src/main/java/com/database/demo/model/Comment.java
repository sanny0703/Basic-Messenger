package com.database.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
	@Column(name="c_id")
	private Integer cId;
	
	@Column(name="u_id")
	private Integer uId;
	
	private Integer id;
	private String comment;
	
	@Column(name="c_author")
	private String author;
	
	@Column(name="date_created")
	private LocalDateTime dateCreated;

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Comment(Integer cId, Integer uId, Integer id, String comment, String author, LocalDateTime dateCreated) {
		super();
		this.cId = cId;
		this.uId = uId;
		this.id = id;
		this.comment = comment;
		this.author = author;
		this.dateCreated = dateCreated;
	}
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
}
