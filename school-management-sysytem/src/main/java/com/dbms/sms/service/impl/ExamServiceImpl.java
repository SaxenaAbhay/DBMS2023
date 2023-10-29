package com.dbms.sms.service.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.dbms.sms.entity.Exam;
import com.dbms.sms.entity.Score;
import com.dbms.sms.repository.ExamRepository;
import com.dbms.sms.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService
{
	private ExamRepository examRepository;
	
	public ExamServiceImpl(ExamRepository ExamRepository) {
		super();
		this.examRepository = ExamRepository;
	}

	@Override
	public List<Exam> getAllExams() {
		
		return examRepository.findAll();
	}

	@Override
	public Exam saveExam(Exam Exam) {
		return examRepository.save(Exam);
	}
	
	@Override
	public Exam getExamById(Long examId) {
		return examRepository.findById(examId).get();
	}

	@Override
	public Exam updateExam(Exam exam) {
		return examRepository.save(exam);
	}

	@Override
	public void deleteExamById(Long examId){
		examRepository.deleteById(examId);
	}
	
	@Override
	public List<Exam> getsearch(String s) {
		if(s==null) return examRepository.findAll();
		
		Long id=Long.parseLong(s);

		return examRepository.findBySubjectId(id);
	} 


}
