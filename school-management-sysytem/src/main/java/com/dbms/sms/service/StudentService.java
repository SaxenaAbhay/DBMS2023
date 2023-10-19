package com.dbms.sms.service;

import java.util.List;

import com.dbms.sms.entity.Student;

public interface StudentService {
	List<Student> getAllStudents();

	Student saveStudent(Student student);

	Student getStudentById(Long scholarId);

	Student updateStudent(Student student);

	void deleteStudentById(Long scholarId);
}


