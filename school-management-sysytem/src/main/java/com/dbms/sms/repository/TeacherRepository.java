package com.dbms.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.repository.CrudRepository;
import com.dbms.sms.entity.Teacher;
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
