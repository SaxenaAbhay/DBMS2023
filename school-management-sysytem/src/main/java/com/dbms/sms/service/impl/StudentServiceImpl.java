package com.dbms.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dbms.sms.entity.Student;
import com.dbms.sms.repository.StudentRepository;
import com.dbms.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService
{
	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		
		return studentRepository.findAll();
	}

}
