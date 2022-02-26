package com.database.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.database.demo.model.Comment;

public interface CommentRepository extends CrudRepository<Comment,Integer> {

	public List<Comment> findAllByOrderByDateCreatedDesc();
	
	@Query(nativeQuery=true,value="select * from comments c where c.id =:id order by c.date_created desc ")
	public List<Comment> getCommentsById(Integer id);
}
