package com.dbms.sms.service;

import com.dbms.sms.entity.Teacher;
import com.dbms.sms.entity.User;
import com.dbms.sms.repository.TeacherRepository;
import com.dbms.sms.repository.StudentRepository;
import com.dbms.sms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository users;

//    @Autowired
//    private TeacherRepository teachers;

    public String getRole(String username) {
        User user = users.getUser(username);

        if (user.getIsAdmin()) {
            return "admin";
        }
//
//        try {
//            teachers.findByEmail(username);
//            return "faculty";
//        } catch (Exception e) {}

        return "teacher";
    }
	
    public void changePassword(String username, User user) {
        user.setUsername(username);
        users.update(user);
    }
}
