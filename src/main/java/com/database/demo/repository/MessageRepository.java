package com.database.demo.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.database.demo.model.Message;

public interface MessageRepository extends CrudRepository<Message,Integer> {

	public List<Message> findAllByOrderByDateCreatedDesc();
	public List<Message> findByuIdOrderByDateCreatedDesc(Integer uId);
	public List<Message> findByNameContainingOrderByDateCreatedDesc(String name);
}
