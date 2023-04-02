package com.example.microgram.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RowExistence {
    private final JdbcTemplate jdbcTemplate;
    public Boolean checkUserExistenceById(Long userId){
        String sql = "SELECT EXISTS(SELECT FROM users\n" +
                "    WHERE user_id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, userId);
    }

    public Boolean checkPublicationExistenceById(Long publicationId){
        String sql = "SELECT EXISTS(SELECT FROM publications\n" +
                "    WHERE publication_id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, publicationId);
    }
}
