package com.example.microgram.daos;

import com.example.microgram.dtos.CommentDto;
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
public class CommentDao {

    private final JdbcTemplate jdbcTemplate;

    public String addComment(CommentDto commentDto, String userEmail){
        String id_sql = "SELECT user_id FROM users WHERE email = ?";
        Long userId = jdbcTemplate.queryForObject(id_sql, Long.class, userEmail);

        String sql = "INSERT INTO comments (user_id, publication_id, comment_text, comment_date) " +
                "VALUES(?,?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1,userId);
                ps.setLong(2,commentDto.getPublicationId());
                ps.setString(3,commentDto.getCommentText());
                ps.setDate(4, Date.valueOf(LocalDate.now()));
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });
        return "Comment added";
    }

    public void deleteComment(Long commentId, String email){
        String sql = "DO $$\n" +
                "DECLARE\n" +
                "    userId BIGINT = (SELECT user_id\n" +
                "                 FROM users\n" +
                "                 WHERE email = " + "'" + email + "'" + ");\n" +
                "    commentId BIGINT = " + commentId + ";\n" +
                "BEGIN\n" +
                "    IF userId = (SELECT user_id\n" +
                "              FROM comments\n" +
                "              WHERE comment_id = commentId)\n" +
                "    THEN\n" +
                "        DELETE FROM likes\n" +
                "        WHERE comment_id = commentId;\n" +
                "\n" +
                "        DELETE FROM comments\n" +
                "        WHERE comment_id = commentId;\n" +
                "\n" +
                "    end if;\n" +
                "end;\n" +
                "$$";

        jdbcTemplate.execute(sql);
    }
}
