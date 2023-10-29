package com.dbms.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dbms.sms.entity.Student;
import com.dbms.sms.repository.StudentRepository;
import com.dbms.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService
{	
	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Long scholarId) {
		return studentRepository.findById(scholarId).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Long scholarId){
		studentRepository.deleteById(scholarId);
	}

	@Override
	public Student getByScholarId(String currentUser) {
		String sql = "SELECT * FROM student WHERE rollNo = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class));
	}
	@Override
	public List<Student> getsearch(String s) {
		if(s==null) return studentRepository.findAll();
		
		Long id=Long.parseLong(s);

		return studentRepository.findById(id).stream().toList();
	}
	
}
