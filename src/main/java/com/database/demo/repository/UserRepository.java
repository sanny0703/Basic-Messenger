package com.database.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.database.demo.model.User;

public interface UserRepository extends CrudRepository<User,Integer> {

	public User findByEmailAndPassword(String email,String password);
	
	public List<User> findByNameContaining(String name);
}
