package com.example.microgram.daos;

import com.example.microgram.dtos.PublicationDto;
import com.example.microgram.entities.Publication;
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
public class PublicationDao {
    private final JdbcTemplate jdbcTemplate;
    public String addPublication(PublicationDto publicationDto, String userEmail){
        String id_sql = "SELECT user_id FROM users WHERE email = ?";
        Long userId = jdbcTemplate.queryForObject(id_sql, Long.class, userEmail);

        String publicationSql = "INSERT INTO publications(user_id,image,description,publication_date) " +
                "VALUES(?,?,?,?)";

        String userSql = "UPDATE users\n" +
                "SET publications = publications + 1\n" +
                "WHERE user_id = " + userId;
        jdbcTemplate.batchUpdate(publicationSql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1,userId);
                ps.setString(2,publicationDto.getImage());
                ps.setString(3,publicationDto.getDescription());
                ps.setDate(4,Date.valueOf(LocalDate.now()));
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });

        jdbcTemplate.execute(userSql);
        return "Publication added";
    }

    public List<Publication> getUsersPublication(Long userId){
        String sql = "SELECT * FROM publications\n" +
                "WHERE user_id = ?";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Publication.class), userId);
    }

    public List<Publication> getNewsline(String email){
        String id_sql = "SELECT user_id FROM users\n" +
                "WHERE email = ?";
        Long user_id = jdbcTemplate.queryForObject(id_sql,Long.class,email);

        String sql = "SELECT *\n" +
                "FROM publications\n" +
                "WHERE user_id = ANY (SELECT subscribedtoid\n" +
                "                      FROM subscriptions\n" +
                "                      WHERE subscribingid = ?)";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Publication.class), user_id);
    }

    public void delete(String email, Long publicationId){
        String sql = "DO $$\n" +
                "DECLARE\n" +
                "    userId BIGINT = (SELECT user_id\n" +
                "                 FROM users\n" +
                "                 WHERE email = " + "'" + email + "'" + ");\n" +
                "    publicationId BIGINT = " + publicationId + ";\n" +
                "BEGIN\n" +
                "    IF userId = (SELECT user_id\n" +
                "              FROM publications\n" +
                "              WHERE publication_id = publicationId)\n" +
                "    THEN\n" +
                "        DELETE FROM comments\n" +
                "        WHERE publication_id = publicationId;\n" +
                "\n" +
                "        DELETE FROM likes\n" +
                "        WHERE publication_id = publicationId;\n" +
                "\n" +
                "        DELETE FROM publications\n" +
                "        WHERE publication_id = publicationId;\n" +
                "\n" +
                "        UPDATE users\n" +
                "        SET publications = publications - 1\n" +
                "        WHERE user_id = userId;\n" +
                "    end if;\n" +
                "end;\n" +
                "$$";

        jdbcTemplate.execute(sql);
    }
}
