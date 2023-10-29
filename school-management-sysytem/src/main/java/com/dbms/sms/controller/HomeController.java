package com.dbms.sms.controller;

import com.dbms.sms.entity.Exam;
import com.dbms.sms.entity.Message;
import com.dbms.sms.entity.Score;
import com.dbms.sms.entity.Student;
import com.dbms.sms.repository.StudentRepository;
import com.dbms.sms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.dbms.sms.service.TeacherService;

import helper.Search;

@Controller
public class HomeController {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    
    @Autowired
    private ClassService classService;
    
    @Autowired
    private StudentService studentService;
  
    @Autowired
    private ScoreService scoreService;
    
    @Autowired
    private ExamService examService;
    
     @RequestMapping("/")
     public String home(HttpSession session,Model model){
    	 String currentUser = authenticationService.getCurrentUser(session);
    	 model.addAttribute("username", currentUser);
         model.addAttribute("userImageUrl", "https://ui-avatars.com/api/?name=" + currentUser);

         String userRole = userService.getRole(currentUser);
         model.addAttribute("userRole", userRole);
	        if (!userRole.equals("admin")) {
//	        	session.setAttribute("message",new Message("You don't have the right to delete student details.","danger"));
	            return "teacher_dashboard";
	        }
        return "dashboard";
     }
     
     @RequestMapping("/terms")
     public String terms() {
         return "terms";
     }
     
     @PostMapping("/classes_search")
     public String classesSearch(@ModelAttribute("show") Search show, Model model) {
    	 String s=show.getFind();
    	 
    	 List<com.dbms.sms.entity.Class> l=classService.getsearch(s);
    	 
    	 model.addAttribute("l",l);
    	 
    	 return "classes";
     }
     @PostMapping("/teachers_search")
     public String teachersSearch(@ModelAttribute("show") Search show, Model model) {
    	 String s=show.getFind();
    	 
    	 List<com.dbms.sms.entity.Teacher> l=teacherService.getsearch(s);
    	 
    	 model.addAttribute("l",l);
    	 
    	 return "teachers";
     }
     @PostMapping("/students_search")
     public String studentsSearch(@ModelAttribute("show") Search show, Model model) {
    	 String s=show.getFind();
    	 
    	 List<Student> l=studentService.getsearch(s);
    	 
    	 model.addAttribute("l",l);
    	 
    	 return "students";
     }
     @PostMapping("/scores_search")
     public String scoresSearch(@ModelAttribute("show") Search show, Model model) {
    	 String s=show.getFind();
    	 
    	 List<Score> l=scoreService.getsearch(s);
    	 
    	 model.addAttribute("l",l);
    	 
    	 return "scores";
     }
     @PostMapping("/exams_search")
     public String examsSearch(@ModelAttribute("show") Search show, Model model) {
    	 String s=show.getFind();
    	 
    	 List<Exam> l=examService.getsearch(s);
    	 
    	 model.addAttribute("l",l);
    	 
    	 return "exams";
     }
    }
    




