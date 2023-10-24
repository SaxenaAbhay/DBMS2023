package com.dbms.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dbms.sms.entity.Class;
import com.dbms.sms.repository.ClassRepository;
import com.dbms.sms.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService
{
	private ClassRepository classRepository;
	
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

}
