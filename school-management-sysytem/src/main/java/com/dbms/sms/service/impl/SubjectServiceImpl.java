package com.dbms.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dbms.sms.entity.Subject;
import com.dbms.sms.repository.SubjectRepository;
import com.dbms.sms.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService
{
	private SubjectRepository subjectRepository;
	
	public SubjectServiceImpl(SubjectRepository subjectRepository) {
		super();
		this.subjectRepository = subjectRepository;
	}

	@Override
	public List<Subject> getAllSubjects() {
		
		return subjectRepository.findAll();
	}

	@Override
	public Subject saveSubject(Subject subject) {
		return subjectRepository.save(subject);
	}

	@Override
	public Subject getSubjectById(Long subId) {
		return subjectRepository.findById(subId).get();
	}

	@Override
	public Subject updateSubject(Subject subject) {
		return subjectRepository.save(subject);
	}

	@Override
	public void deleteSubjectById(Long subId){
		subjectRepository.deleteById(subId);
	}

}