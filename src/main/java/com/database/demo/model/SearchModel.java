package com.database.demo.model;

public class SearchModel {

	private Integer id;
	private String name;
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
	public SearchModel(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public SearchModel() {
		// TODO Auto-generated constructor stub
	}
}
