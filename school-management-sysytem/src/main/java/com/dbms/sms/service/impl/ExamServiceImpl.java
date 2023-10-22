package com.dbms.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dbms.sms.entity.Exam;
import com.dbms.sms.repository.ExamRepository;
import com.dbms.sms.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService
{
	private ExamRepository ExamRepository;
	
	public ExamServiceImpl(ExamRepository ExamRepository) {
		super();
		this.ExamRepository = ExamRepository;
	}

	@Override
	public List<Exam> getAllExams() {
		
		return ExamRepository.findAll();
	}

	@Override
	public Exam saveExam(Exam Exam) {
		return ExamRepository.save(Exam);
	}


}
