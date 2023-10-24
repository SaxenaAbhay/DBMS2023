//package com.dbms.sms.controller;
//
////import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.dbms.sms.entity.Student;
//import com.dbms.sms.repository.StudentRepository;
//import com.fasterxml.jackson.annotation.JsonCreator.Mode;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//
////@Controller
////public class HomeController {
////
////    @Autowired
////    private StudentRepository studentRepository;
////
////     @RequestMapping("/")
////     public String home(){
////        return "home";
////     }
////     
////     @RequestMapping("/terms")
////     public String terms() {
////         return "terms";
////     }
////    }
////
//package com.dbms.sms.controller;
//

//import com.dbms.sms.entity.Session;
//import com.dbms.sms.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.CustomDateEditor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpSession;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//public class HomeController extends BaseController {
//
//    // Needed to automatically convert String date in form to Date object.
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
//    }
//
//    @GetMapping("/")
//    public String home(Model model, HttpSession session) {
//        addDefaultAttributes(model, session);
//
//        model.addAttribute("majors", majorService.getAllMajors());
//        model.addAttribute("faculties", facultyService.getAllFaculties());
//        return  "site/home";
//    }
//
//    @GetMapping("/registration")
//    public String registration(Model model, HttpSession session, RedirectAttributes attributes) {
//        addDefaultAttributes(model, session);
//
//        List<Session> sessionList = sessionService.getAllSessions();
//
//        Boolean registrationsOpen = false;
//
//        for (int i = 0; i < sessionList.size(); i++) {
//            Session sessionObj = sessionList.get(i);
//            if (sessionObj.getRegistrationsOpen()) {
//                registrationsOpen = true;
//                break;
//            }
//        }
//
//        if (!registrationsOpen) {
//            toastService.redirectWithErrorToast(attributes, "Registrations closed.");
//            return "redirect:/";
//        }
//
//        model.addAttribute("sessions", sessionList);
//        model.addAttribute("application", new RegistrationApplication());
//        return  "site/registration";
//    }
//
//    @PostMapping("/registration")
//    public String postRegistration(@ModelAttribute RegistrationApplication application, Model model, HttpSession session, RedirectAttributes attributes) {
//        registrationApplicationService.createApplication(application);
//        toastService.redirectWithSuccessToast(attributes, "Your application was successfully submitted.");
//        return  "redirect:/";
//    }
//}
//
