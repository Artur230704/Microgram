package com.example.microgram.daos;

import com.example.microgram.dtos.user.UserLoginDTO;
import com.example.microgram.dtos.user.UserRegisterDTO;
import com.example.microgram.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
@AllArgsConstructor
@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final PasswordEncoder encoder;

    public void register(UserRegisterDTO user){
        String registerSql = "INSERT INTO users(username, email, user_password, user_roles, enabled, publications, subscriptions, subscribers) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        String[] roles = {"USER"};
        jdbcTemplate.batchUpdate(registerSql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,user.getUsername());
                ps.setString(2,user.getEmail());
                ps.setString(3,encoder.encode(user.getPassword()));
                ps.setArray(4, ps.getConnection().createArrayOf("text", roles));
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

    public boolean login(UserLoginDTO user){
        String sql = "SELECT EXISTS(SELECT * FROM users\n" +
                "    WHERE email = ?\n" +
                "    AND user_password = ?)";

        String encodedPassword = encoder.encode(user.getPassword());

        return jdbcTemplate.queryForObject(sql, Boolean.class, user.getEmail(), encodedPassword);
    }
}
