package com.dbms.sms.service;

import java.util.List;

import com.dbms.sms.entity.Subject;

public interface SubjectService {
	List<Subject> getAllSubjects();

	Subject saveSubject(Subject subject);

	Subject getSubjectById(Long subId);

	Subject updateSubject(Subject subject);

	void deleteSubjectById(Long subId);
}


