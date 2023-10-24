package com.dbms.sms;

//import java.time.LocalDate;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dbms.sms.entity.Student;
import com.dbms.sms.repository.StudentRepository;


@SpringBootApplication
public class SchoolManagementSysytemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementSysytemApplication.class, args);
	}
	
}
