package com.dbms.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.repository.CrudRepository;
import com.dbms.sms.entity.Teacher;
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
}

//package com.dbms.sms.repository;
//
//import com.dbms.sms.entity.Teacher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class TeacherRepository {
//    @Autowired
//    private JdbcTemplate template;
//
//    public List<Teacher> getAll() {
//        String sql = "SELECT * FROM faculty;";
//        return template.query(sql, new BeanPropertyRowMapper<>(Teacher.class));
//    }
//
//    public void createFaculty(Teacher faculty, String username) {
//        String sql = "INSERT INTO faculty (name, email, bio, address, phone, dob, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        template.update(
//                sql, faculty.getFirstname(), faculty.getEmail(),
//                faculty.getAddress(), faculty.getContact());
//    }
//
//    public Teacher getById(int id) {
//        String sql = "SELECT * FROM faculty WHERE id = ?";
//        return template.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<>(Teacher.class));
//    }
//
//    public void deleteFaculty(int id) {
//        String sql = "DELETE FROM faculty WHERE id = ?";
//        template.update(sql, id);
//    }
//
//    public Teacher getByEmail(String email) {
//        String sql = "SELECT * FROM faculty WHERE email = ?";
//        return template.queryForObject(sql, new Object[] {email}, new BeanPropertyRowMapper<>(Teacher.class));
//    }
//
//    public void update(Teacher faculty) {
//        String sql = "UPDATE faculty SET name=?, email=?, contact=?, address=? WHERE id = ?";
//
//        template.update(
//                sql, faculty.getFirstname(), faculty.getEmail(), faculty.getContact(), faculty.getAddress(),
//                faculty.getEmployeeId());
//    }
//}
