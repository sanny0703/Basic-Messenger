package com.database.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Message {
	
	@Id
	@GeneratedValue(strategy= javax.persistence.GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String message;
	
	@Column(name="date_created")
	private LocalDateTime dateCreated;
	
	@Column(name="u_id")
	private Integer uId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public Message(Integer id, String name, String message, LocalDateTime dateCreated, Integer uId) {
		super();
		this.id = id;
		this.name = name;
		this.message = message;
		this.dateCreated = dateCreated;
		this.uId = uId;
	}
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
}
