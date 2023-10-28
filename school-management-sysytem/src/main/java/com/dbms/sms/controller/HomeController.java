package com.dbms.sms.controller;

import com.dbms.sms.entity.Message;
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

@Controller
public class HomeController {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private UserService userService;

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
    }

//@Controller
//public class HomeController extends BaseController {
//	
//	@Autowired
//	private TeacherService teacherService;
//    // Needed to automatically convert String date in form to Date object.
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
//    }
//
//    @GetMapping("/")
//    public String home(Model model, HttpSession session) {
////        addDefaultAttributes(model, session);
//
////        model.addAttribute("majors", majorService.getAllMajors());
//        model.addAttribute("faculties", teacherService.getAllteachers());
//        return  "dashboard";
//    }
//
//}

