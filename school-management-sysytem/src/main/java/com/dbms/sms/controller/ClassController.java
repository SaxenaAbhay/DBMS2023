package com.dbms.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dbms.sms.entity.Class;
import com.dbms.sms.service.ClassService;

@Controller
public class ClassController {
	
	private ClassService classService;

	public ClassController(ClassService classService) {
		super();
		this.classService = classService;
	}
	
	//handler method to handle list Classs and return mode and view
	
	@GetMapping("/classes")
	public String listClasses(Model model) {
		model.addAttribute("classes", classService.getAllClasses());
		return "classes";
	}


	@GetMapping("/classes/new")
	public String createClassForm(Model model){
		Class classs= new Class();
		model.addAttribute("classes", classs);
		return "create_class";
	}

	@PostMapping("/classes")
	public String saveClass(@ModelAttribute("classs") Class classs){
		classService.saveClass(classs);
		return "redirect:/classes";
	}

		
	@GetMapping("/classes/edit/{classId}")
	public String editSExamForm(@PathVariable Long classId, Model model){
		model.addAttribute("classs", classService.getClassById(classId));
		return "edit_class";
	}

	@PostMapping("/classes/{classId}")
	public String updateClass(@PathVariable Long classId, @ModelAttribute("classs") Class classs, Model model){
		Class existingclass=classService.getClassById(classId);
		existingclass.setClassId(classId);
		existingclass.setSection(classs.getSection());
		existingclass.setStandard(classs.getStandard());

		classService.updateClass(existingclass);
		return "redirect:/classes";
	}

	@GetMapping("/classes/{classId}")
	public String deleteString(@PathVariable Long classId){
		classService.deleteClassById(classId);
		return "redirect:/classes";
	}
}
