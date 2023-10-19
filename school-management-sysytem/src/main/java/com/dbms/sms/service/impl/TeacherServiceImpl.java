package com.dbms.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dbms.sms.entity.Teacher;
import com.dbms.sms.repository.TeacherRepository;
import com.dbms.sms.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService
{
	private TeacherRepository teacherRepository;
	
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}

	@Override
	public List<Teacher> getAllteachers() {
		
		return teacherRepository.findAll();
	}

	@Override
	public Teacher saveteacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher getteacherById(Long employeeId) {
		return teacherRepository.findById(employeeId).get();
	}

	@Override
	public Teacher updateteacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public void deleteteacherById(Long employeeId){
		teacherRepository.deleteById(employeeId);
	}


}
