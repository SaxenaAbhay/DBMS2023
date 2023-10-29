package com.dbms.sms.service;

import java.util.List;

import com.dbms.sms.entity.Teacher;

public interface TeacherService {
	List<Teacher> getAllteachers();

	Teacher saveteacher(Teacher teacher);

	Teacher getteacherById(Long employeeId);

	Teacher updateteacher(Teacher teacher);

	void deleteteacherById(Long employeeId);
	
	Teacher getTeacherByEmail(String email);

	public List<Teacher> getsearch(String s);
}


