package com.dbms.sms.service;

import java.util.List;

import com.dbms.sms.entity.Exam;
import com.dbms.sms.entity.Student;

public interface ExamService {
	List<Exam> getAllExams();

	Exam saveExam(Exam exam);

	Exam getExamById(Long examId);

	Exam updateExam(Exam exam);

	void deleteExamById(Long examId);

	public List<Exam> getsearch(String s);

//	Student getByScholarId(String currentUser);
}


