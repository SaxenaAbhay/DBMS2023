package com.dbms.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dbms.sms.entity.Student;
import com.dbms.sms.repository.StudentRepository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private StudentRepository studentRepository;

     @RequestMapping("/")
     public String home(){
        return "home";
     }
     
     @RequestMapping("/terms")
     public String terms() {
         return "terms";
     }
    }

