package com.dbms.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dbms.sms.entity.Class;
import com.dbms.sms.repository.ClassRepository;
import com.dbms.sms.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private JdbcTemplate template;
	
	public ClassServiceImpl(ClassRepository classRepository) {
		super();
		this.classRepository = classRepository;
	}

	@Override
	public List<Class> getAllClasses() {

		return classRepository.findAll();
	}

	@Override
	public Class saveClass(Class classs) {
		return classRepository.save(classs);
	}

	@Override
	public Class getClassById(Long classId) {
		return classRepository.findById(classId).get();
	}

	@Override
	public Class updateClass(Class classId) {
		return classRepository.save(classId);
	}

	@Override
	public void deleteClassById(Long classId) {
		classRepository.deleteById(classId);
	}

	@Override
	public List<Class> getsearch(String s) {
		String pattern="\'%"+s+"%\';";
		String sql = "Select * from class where standard Like "+pattern;
		return template.query(sql, new BeanPropertyRowMapper<>(Class.class));

	}
}