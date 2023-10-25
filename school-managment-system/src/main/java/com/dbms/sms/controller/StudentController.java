package com.dbms.sms.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dbms.sms.entity.Student;
import com.dbms.sms.repository.ClassRepository;
import com.dbms.sms.service.ClassService;
import com.dbms.sms.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private ClassRepository classRepository;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	//handler method to handle list students and return mode and view
	
	@GetMapping("/students")
	public String listStudetns(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}


	@GetMapping("/students/new")
	public String createStudentForm(Model model){
		model.addAttribute("listClass", classRepository.findAll());
		Student student= new Student();
		model.addAttribute("student", student);
		return "create_student";
	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student){
		studentService.saveStudent(student);
		return "redirect:/students";
	}

	@GetMapping("/students/edit/{scholarId}")
	public String editStudentForm(@PathVariable Long scholarId, Model model){
		model.addAttribute("student", studentService.getStudentById(scholarId));
		return "edit_student";
	}

	@PostMapping("/students/{scholarId}")
	public String updateStudent(@PathVariable Long scholarId, @ModelAttribute("student") Student student, Model model){
		Student existingStudent=studentService.getStudentById(scholarId);
		existingStudent.setScholarId(scholarId);
		existingStudent.setFirstname(student.getFirstname());
		existingStudent.setLastname(student.getLastname());
		existingStudent.setEmail(student.getEmail());

		studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}

	@GetMapping("/students/{scholarId}")
	public String deleteString(@PathVariable Long scholarId){
		studentService.deleteStudentById(scholarId);
		return "redirect:/students";
	}
}