package com.revature.controllers;
import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"content-type","Authorization"},exposedHeaders = {"Authorization","Info","UserFirstName","UserLastName","UserName"}, methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService service;
	
	@Autowired
	public UserController(UserService userService) {
		this.service = userService;
	}
	
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAll(){
		return service.getAll();
	}
	
//	GET USER BY ID NEEDS TO BE LIKE THIS TO WORK
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUserById(@PathVariable int id) {
		User user = service.getById(id);
		return user;
	}
	//ADD USER
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@RequestBody User newUser) {
		return service.add(newUser);
	}
	//UPDATE USER
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PatchMapping(consumes="application/json", produces="application/json")
	public User updateUser(@RequestBody User updateUser) {
		User user = service.update(updateUser);
		return user;
	}
	
	//TODO Throws an exception, possibly because user has no posts
	@DeleteMapping(value="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable int id) {
		service.delete(id);
	}
	

}
