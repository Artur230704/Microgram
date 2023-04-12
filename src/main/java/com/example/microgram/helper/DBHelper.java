package com.example.microgram.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DBHelper {
    private final JdbcTemplate jdbcTemplate;
    public Boolean getUserExistenceById(Long userId){
        String sql = "SELECT EXISTS(SELECT FROM users\n" +
                "    WHERE user_id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, userId);
    }

    public Boolean getPublicationExistenceById(Long publicationId){
        String sql = "SELECT EXISTS(SELECT FROM publications\n" +
                "    WHERE publication_id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, publicationId);
    }

    public Boolean getCommentExistenceById(Long commentId){
        String sql = "SELECT EXISTS(SELECT FROM comments\n" +
                "    WHERE comment_id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, commentId);
    }

    public Long getUserIdByEmail(String email){
        String id_sql = "SELECT user_id FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(id_sql, Long.class, email);
    }
}
