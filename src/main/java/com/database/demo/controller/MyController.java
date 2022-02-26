package com.database.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.database.demo.faker.FakeMessages;
import com.database.demo.model.Comment;
import com.database.demo.model.Message;
import com.database.demo.model.ResponseStatus;
import com.database.demo.model.SearchModel;
import com.database.demo.model.User;
import com.database.demo.repository.CommentRepository;
import com.database.demo.repository.MessageRepository;
import com.database.demo.repository.UserRepository;
import com.github.javafaker.Faker;

@RestController
@CrossOrigin(origins="*")
public class MyController {

	@Autowired
	private  MessageRepository messageRepository ;
	
	@Autowired
	private  CommentRepository commentRepository ;
	
	@Autowired
	private  UserRepository userRepository ;
	
	//Users Operations
	
	
	@PostMapping("users/add")
	public ResponseStatus addUser(@RequestBody User user) {
		 User my_user = userRepository.save(user);
		 ResponseStatus rs = new ResponseStatus();
		 if(my_user!=null &&  my_user.getuId()>0) {
			 rs.setStatus("Success");
			 return rs;
		 }
		 else {
			 rs.setStatus("Failure");
			 return rs;
		 }
	}
	@PostMapping("users/findUser")
	public User findUser(@RequestBody User user) {
		return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}
	@PostMapping("users/delete")
	public ResponseStatus deleteUser(@RequestBody SearchModel sm) {
		 ResponseStatus rs = new ResponseStatus();
		 try {
		userRepository.deleteById(sm.getId());
		rs.setStatus("Success");
		return rs;
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 rs.setStatus("Failure");
			 return rs;
		 }
	}
	@GetMapping("users/view_all")
	public List<User> getUsers(){
		return (List<User>)userRepository.findAll();
	}
	@PostMapping("users/searchByName")
	public List<User> searchUsersByName(@RequestBody SearchModel sm ){
		return userRepository.findByNameContaining(sm.getName());
	}
	
	// Messages Operations
	
	
	@PostMapping("messages/add")
	public ResponseStatus addMessage(@RequestBody Message message) {
		 LocalDateTime date = LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 String dateTime = formatter.format(date);
		 message.setDateCreated(LocalDateTime.parse(dateTime,formatter));
		 Message my_message = messageRepository.save(message);
		 ResponseStatus rs = new ResponseStatus();
		 if(my_message!=null && my_message.getId()>0) {
			 rs.setStatus("Success");
			 return rs;
		 }
		 else {
			 rs.setStatus("Failure");
			 return rs;
		 }
	}
	@GetMapping("messages/view_all")
	public List<Message> getMessages(){
		return (List<Message>)messageRepository.findAllByOrderByDateCreatedDesc();
	}
	@PostMapping("messages/searchByName")
	public List<Message> getMessagesByName(@RequestBody SearchModel sm){
		return (List<Message>)messageRepository.findByNameContainingOrderByDateCreatedDesc(sm.getName());
		
	}
	@PostMapping("messages/searchByuId")
	public List<Message> getMessagesByuId(@RequestBody SearchModel sm){
		return (List<Message>)messageRepository.findByuIdOrderByDateCreatedDesc(sm.getId());
		
	}
	@PostMapping("messages/delete")
	public ResponseStatus deleteMessage(@RequestBody SearchModel sm) {
		 ResponseStatus rs = new ResponseStatus();
		 try {
		messageRepository.deleteById(sm.getId());
		rs.setStatus("Success");
		return rs;
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 rs.setStatus("Failure");
			 return rs;
		 }
	}
	@PostMapping("messages/update")
	public ResponseStatus updateMessage(@RequestBody SearchModel sm) {
		 ResponseStatus rs = new ResponseStatus();
		if(messageRepository.findById(sm.getId()).isPresent()) {
			Message m = messageRepository.findById(sm.getId()).get();
			m.setMessage(sm.getName());
			messageRepository.save(m);
			rs.setStatus("Success");
			return rs;
		}
		else {
			rs.setStatus("Failure");
			 return rs;
		}
	}
	
	
	//Comments Operatins
	
	
	@PostMapping("comments/add")
	public ResponseStatus addComment(@RequestBody Comment comment) {
		 LocalDateTime date = LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 String dateTime = formatter.format(date);
		 comment.setDateCreated(LocalDateTime.parse(dateTime,formatter));
		 Comment my_comment = commentRepository.save(comment);
		 ResponseStatus rs = new ResponseStatus();
		 if(my_comment!=null && my_comment.getId()>0) {
			 rs.setStatus("Success");
			 return rs;
		 }
		 else {
			 rs.setStatus("Failure");
			 return rs;
		 }
	}
	
	@GetMapping("comments/view_all")
	public List<Comment> getComments(){
		return (List<Comment>)commentRepository.findAllByOrderByDateCreatedDesc();
	}
	
	@PostMapping("comments/update")
	public ResponseStatus updateComment(@RequestBody SearchModel sm) {
		 ResponseStatus rs = new ResponseStatus();
		if(commentRepository.findById(sm.getId()).isPresent()) {
			Comment c = commentRepository.findById(sm.getId()).get();
			c.setComment(sm.getName());
			commentRepository.save(c);
			rs.setStatus("Success");
			return rs;
		}
		else {
			rs.setStatus("Failure");
			 return rs;
		}
	}
	
	@PostMapping("comments/findById")
	public List<Comment> getCommentsById(@RequestBody SearchModel sm){
		return (List<Comment>) commentRepository.getCommentsById(sm.getId());
	}
	
	@PostMapping("comments/delete")
	public ResponseStatus deleteComment(@RequestBody SearchModel sm) {
		 ResponseStatus rs = new ResponseStatus();
		 try {
		commentRepository.deleteById(sm.getId());
		rs.setStatus("Success");
		return rs;
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 rs.setStatus("Failure");
			 return rs;
		 }
	}
	
	
	/* Adding some fake Random data for testing*/
	
	// Fake Users
	@GetMapping("users/addFake")
	public void addFakeUsers() {
		for(int i =0;i<200;i++) {
			Faker faker = new Faker();
			User user = new User();
			user.setName(faker.name().fullName());
			user.setEmail(user.getName()+"@gmail.com");
			user.setPassword(faker.lorem().characters(8, 16));
			userRepository.save(user);
		}
	}
	
	//Fake Messages
	@GetMapping("messages/addFake")
	public void addFakeMessages() {
		FakeMessages fm = new FakeMessages();
		for(int i =0;i<200;i++) {
			Message m = new Message();
			User u = userRepository.findById(i+21).get();
			m.setName(u.getName());
			m.setMessage("Hi All!!"+u.getName()+"Here!@!#"+"/n"+fm.randomString(50));
			m.setuId(u.getuId());
			LocalDateTime date = LocalDateTime.now();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			 String dateTime = formatter.format(date);
			 m.setDateCreated(LocalDateTime.parse(dateTime,formatter));
			messageRepository.save(m);
		}
	}
	@GetMapping("comments/addFake/{uId}")
	public void addFakeComments(@PathVariable("uId") int id) {
		FakeMessages fm = new FakeMessages();
		for(int i =0;i<200;i++) {
			Comment c = new Comment();
			User u = userRepository.findById(id).get();
			c.setAuthor(u.getName());
			c.setuId(id);
			c.setId(i+20);
			c.setComment("Hello !!"+fm.randomString(50));
			LocalDateTime date = LocalDateTime.now();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			 String dateTime = formatter.format(date);
			 c.setDateCreated(LocalDateTime.parse(dateTime,formatter));
			commentRepository.save(c);
		}
	}
}
