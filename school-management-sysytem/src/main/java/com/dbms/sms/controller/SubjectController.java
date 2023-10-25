package com.dbms.sms.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dbms.sms.entity.Subject;
import com.dbms.sms.repository.ClassRepository;
import com.dbms.sms.service.ClassService;
import com.dbms.sms.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ClassRepository classRepository;

	public SubjectController(SubjectService subjectService) {
		super();
		this.subjectService = subjectService;
	}
	//handler method to handle list subjects and return mode and view
	
	@GetMapping("/subjects")
	public String listSubjects(Model model) {
		model.addAttribute("subjects", subjectService.getAllSubjects());
		return "subjects";
	}


	@GetMapping("/subjects/new")
	public String createsubjectForm(Model model){
		model.addAttribute("listClass", classRepository.findAll());
		Subject subject= new Subject();
		model.addAttribute("subject", subject);
		return "create_subject";
	}

	@PostMapping("/subjects")
	public String savesubject(@ModelAttribute("subject") Subject subject){
		subjectService.saveSubject(subject);
		return "redirect:/subjects";
	}

	@GetMapping("/subjects/edit/{subId}")
	public String editsubjectForm(@PathVariable Long subId, Model model){
		model.addAttribute("subject", subjectService.getSubjectById(subId));
		return "edit_subject";
	}

	@PostMapping("/subjects/{subId}")
	public String updatesubject(@PathVariable Long subId, @ModelAttribute("subject") Subject subject, Model model){
		Subject existingsubject=subjectService.getSubjectById(subId);
		existingsubject.setSubId(subId);
		existingsubject.setSub_name(subject.getSub_name());

		subjectService.updateSubject(existingsubject);
		return "redirect:/subjects";
	}

	@GetMapping("/subjects/{subId}")
	public String deleteString(@PathVariable Long subId){
		subjectService.deleteSubjectById(subId);
		return "redirect:/subjects";
	}
}
