package com.dbms.sms.controller;

import com.dbms.sms.entity.Message;
import com.dbms.sms.entity.User;
import com.dbms.sms.service.AuthenticationService;
import com.dbms.sms.service.ToastService;
import com.dbms.sms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private ToastService toastService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (authenticationService.isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("credentials", new User());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute User credentials, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
//        if (authenticationService.isAuthenticated(session)) {
//            return "redirect:/";
//        }

        String username = credentials.getUsername();
        
        
        String password = credentials.getPassword();
//        
//        System.out.println(username);
//        System.out.println(password);
        String errorMessage=null;
        try {
            if (authenticationService.checkCredentials(username, password)) {
            	String currentUser = authenticationService.getCurrentUser(session);
            	model.addAttribute("username", currentUser);
                authenticationService.loginUser(session, username);
                String userRole = userService.getRole(username);
//                model.addAttribute("userRole", userRole);
       	        if (!userRole.equals("admin")) {
       	            return "teacher_dashboard";
       	        }
                return "dashboard";
            }
            errorMessage = "Incorrect password.";
        } catch (Exception e) {
            errorMessage = "No user with this username found";
        }
        if(errorMessage!=null) {
        	session.setAttribute("message",new Message(errorMessage, "danger"));
        }
        model.addAttribute("credentials", credentials);
//        toastService.displayErrorToast(model, errorMessage);
        return "/login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        authenticationService.logoutUser(session);
        return "redirect:/login";
    }
    
//    @GetMapping("/change_password")
//    public String changePassword(Model model, HttpSession session) {
//        authenticationService.logoutUser(session);
//        return "redirect:/login";
//    }
//    
    
     
}
