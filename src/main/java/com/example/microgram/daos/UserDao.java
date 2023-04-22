package com.example.microgram.daos;

import com.example.microgram.dtos.user.UserRegisterDTO;
import com.example.microgram.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
@AllArgsConstructor
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final PasswordEncoder encoder;

    public void register(UserRegisterDTO user){
        String registerSql = "INSERT INTO users(username, email, user_password, user_role, enabled, publications, subscriptions, subscribers) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(registerSql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,user.getUsername());
                ps.setString(2,user.getEmail());
                ps.setString(3,encoder.encode(user.getPassword()));
                ps.setString(4,"USER");
                ps.setBoolean(5,true);
                ps.setInt(6,0);
                ps.setInt(7,0);
                ps.setInt(8,0);
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });
    }

    public List<User> findUserByName(String username){
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(User.class), username);
    }

    public List<User> findUserByEmail(String email){
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(User.class), email);
    }
}
