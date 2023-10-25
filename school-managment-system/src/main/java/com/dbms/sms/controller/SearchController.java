package com.dbms.sms.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dbms.sms.entity.Student;
import com.dbms.sms.entity.User;
import com.dbms.sms.repository.StudentRepository;
import com.dbms.sms.repository.UserRepository;

@RestController
public class SearchController{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	
	
	//search handler
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query){
		System.out.println(query);
		
		//User user = this.userRepository.getUser(principal.getName());
		List<Student> students = this.studentRepository.findByfirstnameContaining(query);
		return ResponseEntity.ok(students);
		
	}
	
}
