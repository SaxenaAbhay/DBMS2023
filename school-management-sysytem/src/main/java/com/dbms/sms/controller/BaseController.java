package com.dbms.sms.controller;

import com.dbms.sms.service.AuthenticationService;
import com.dbms.sms.service.TeacherService;
import com.dbms.sms.service.StudentService;
import com.dbms.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

abstract class BaseController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
	protected StudentService studentService;

    @Autowired
    private TeacherService facultyService;

    public Boolean isAuthenticated(HttpSession session) {
        return authenticationService.isAuthenticated(session);
    }

    public void addDefaultAttributes(Model model, HttpSession session) {
        String currentUser = authenticationService.getCurrentUser(session);
        if (currentUser != null) {
            model.addAttribute("username", currentUser);
            model.addAttribute("userImageUrl", "https://ui-avatars.com/api/?name=" + currentUser);

            String userRole = userService.getRole(currentUser);
            model.addAttribute("userRole", userRole);
//
//            if (userRole.equals("student")) {
//                model.addAttribute("userProfile", studentService.getByScholarId(currentUser));
//            }

            if (userRole.equals("faculty")) {
                model.addAttribute("userProfile", facultyService.getTeacherByEmail(currentUser));
            }
        }
    }
}
