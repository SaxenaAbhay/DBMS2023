package com.dbms.sms.repository;

import com.dbms.sms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate template;

    public void createUser(String username, String password) {
        String sql = "INSERT INTO user (username, password, isAdmin) VALUES ("+username+","+password+",0);";
        template.update(sql, username, password);
    }

    public User getUser(String username) {
        String sql = "SELECT * FROM user WHERE username =\""+username+"\"";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public void update(User user) {
        String sql = "UPDATE user SET password ="+ user.getPassword()+"WHERE username ="+user.getUsername()+";";
        template.update(sql, user.getPassword(), user.getUsername());
    }
}
//"select * from assignment where username='"+username+"';";