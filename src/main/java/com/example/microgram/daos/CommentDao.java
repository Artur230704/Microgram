package com.example.microgram.daos;

import com.example.microgram.dtos.comment.CommentAddingDTO;
import com.example.microgram.dtos.comment.CommentDisplayDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class CommentDao {

    private final JdbcTemplate jdbcTemplate;

    public String addComment(CommentAddingDTO commentAddingDTO, Long userId){
        String sql = "INSERT INTO comments (user_id, publication_id, comment_text, comment_date) " +
                "VALUES(?,?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1,userId);
                ps.setLong(2,commentAddingDTO.getPublicationId());
                ps.setString(3,commentAddingDTO.getCommentText());
                ps.setDate(4,Date.valueOf(LocalDate.now()));
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });
        return "Comment added";
    }

    public List<CommentDisplayDTO> getComments(Long publicationId){
        String sql = "SELECT email,publication_id,comment_text FROM comments\n" +
                "INNER JOIN users ON users.user_id = comments.user_id " +
                "WHERE publication_id = ?";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(CommentDisplayDTO.class), publicationId);
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
