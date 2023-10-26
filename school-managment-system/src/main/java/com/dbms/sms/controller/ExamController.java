package com.dbms.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dbms.sms.entity.Exam;
import com.dbms.sms.entity.Subject;
import com.dbms.sms.repository.SubjectRepository;
import com.dbms.sms.service.ExamService;


@Controller
public class ExamController {

    private ExamService examService;

	public ExamController(ExamService examService) {
		super();
		this.examService = examService;
	}
    @GetMapping("/exams")
	public String listExams(Model model) {
		model.addAttribute("exams", examService.getAllExams());
		return "exams";
	}


	@GetMapping("/exams/new")
	public String createexamForm(Model model){
		Exam exam= new Exam();
		model.addAttribute("exam", exam);
		return "create_exam";
	}

	@PostMapping("/exams")
	public String saveexam(@ModelAttribute("exam") Exam exam){
		examService.saveExam(exam);
		return "redirect:/exams";
	}
	
	@GetMapping("/scores/edit/{id}")
	public String editscoreForm(@PathVariable Long examId, Model model){
		model.addAttribute("exam", examService.getExamById(examId));
		return "edit_exam";
	}
}
