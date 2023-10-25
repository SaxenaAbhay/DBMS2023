package com.dbms.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dbms.sms.entity.Teacher;
import com.dbms.sms.service.TeacherService;

@Controller
public class TeacherController {
	
	private TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		super();
		this.teacherService = teacherService;
	}
	
	//handler method to handle list teachers and return mode and view
	
	@GetMapping("/teachers")
	public String listStudetns(Model model) {
		model.addAttribute("teachers", teacherService.getAllteachers());
		return "teachers";
	}


	@GetMapping("/teachers/new")
	public String createteacherForm(Model model){
		Teacher teacher= new Teacher();
		model.addAttribute("teacher", teacher);
		return "create_teacher";
	}

	@PostMapping("/teachers")
	public String saveteacher(@ModelAttribute("teacher") Teacher teacher){
		teacherService.saveteacher(teacher);
		return "redirect:/teachers";
	}

	@GetMapping("/teachers/edit/{employeeId}")
	public String editteacherForm(@PathVariable Long employeeId, Model model){
		model.addAttribute("teacher", teacherService.getteacherById(employeeId));
		return "edit_teacher";
	}

	@PostMapping("/teachers/{employeeId}")
	public String updateteacher(@PathVariable Long employeeId, @ModelAttribute("teacher") Teacher teacher, Model model){
		Teacher existingteacher=teacherService.getteacherById(employeeId);
        existingteacher.setEmployeeId(employeeId);
		existingteacher.setFirstname(teacher.getFirstname());
		existingteacher.setLastname(teacher.getLastname());
		existingteacher.setEmail(teacher.getEmail());

        
		teacherService.updateteacher(existingteacher);
		return "redirect:/teachers";
	}

	@GetMapping("/teachers/{employeeId}")
	public String deleteString(@PathVariable Long employeeId){
		teacherService.deleteteacherById(employeeId);
		return "redirect:/teachers";
	}
}
