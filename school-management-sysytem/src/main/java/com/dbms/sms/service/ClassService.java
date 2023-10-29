package com.dbms.sms.service;

import java.util.List;

import com.dbms.sms.entity.Class;
import com.dbms.sms.entity.Exam;


public interface ClassService {
    List<Class> getAllClasses();

	Class saveClass(Class classs);

	Class getClassById(Long classId);

	Class updateClass(Class classs);

	void deleteClassById(Long classId);

	public List<Class> getsearch(String s);


}