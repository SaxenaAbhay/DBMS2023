package com.dbms.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.repository.CrudRepository;

import com.dbms.sms.entity.Student;
import com.dbms.sms.entity.User;
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	//search
	public List<Student> findByfirstnameContaining(String name);
}
