package com.example.microgram.daos;

import com.example.microgram.dtos.LikeDto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class LikeDao {
    private final JdbcTemplate jdbcTemplate;

    public String likePublication(LikeDto likeDto, Long userId){
        String sql = "INSERT INTO likes (user_id, object_type, publication_id, comment_id, like_date)\n" +
                "VALUES (?,?,?,null,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1,userId);
                ps.setString(2,likeDto.getObjectType());
                ps.setLong(3,likeDto.getPublicationId());
                ps.setDate(4, Date.valueOf(LocalDate.now()));
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });

        return "You liked this publication";
    }
    public String likeComment(LikeDto likeDto, Long userId){
        String sql = "INSERT INTO likes (user_id, object_type, publication_id, comment_id, like_date)\n" +
                "VALUES (?,?,null,?,?);\n";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1,userId);
                ps.setString(2,likeDto.getObjectType());
                ps.setLong(3,likeDto.getCommentId());
                ps.setDate(4, Date.valueOf(LocalDate.now()));
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });

        return "You liked this comment";
    }
}
