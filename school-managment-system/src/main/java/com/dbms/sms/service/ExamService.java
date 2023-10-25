package com.dbms.sms.service;

import java.util.List;

import com.dbms.sms.entity.Exam;

public interface ExamService {
	List<Exam> getAllExams();

	Exam saveExam(Exam exam);
}


