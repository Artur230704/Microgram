package com.example.microgram.daos;

import com.example.microgram.dtos.publication.PublicationAddingDTO;
import com.example.microgram.dtos.publication.PublicationDisplayDTO;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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

    public List<PublicationDisplayDTO> getAll(){
        String sql = "SELECT publication_id,email,image,description FROM publications\n" +
                "INNER JOIN users on users.user_id = publications.user_id";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(PublicationDisplayDTO.class));
    }

    public void addPublication(PublicationAddingDTO publicationDto, Long userId){
        String publicationSql = "INSERT INTO publications(user_id,image,description,publication_date) " +
                "VALUES(?,?,?,?)";

        String userSql = "UPDATE users\n" +
                "SET publications = publications + 1\n" +
                "WHERE user_id = " + userId;
        jdbcTemplate.batchUpdate(publicationSql, new BatchPreparedStatementSetter() {
            @SneakyThrows
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1,userId);
                ps.setBytes(2,publicationDto.getImageBytes());
                ps.setString(3,publicationDto.getDescription());
                ps.setDate(4,Date.valueOf(LocalDate.now()));
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });

        jdbcTemplate.execute(userSql);
    }

    public PublicationDisplayDTO getAddedPublication(){
        String sql = "SELECT publication_id,email,image,description\n" +
                "FROM publications\n" +
                "INNER JOIN users on users.user_id = publications.user_id\n" +
                "ORDER BY publication_id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(PublicationDisplayDTO.class));
    }

    public List<PublicationDisplayDTO> getUsersPublication(Long userId){
        String sql = "SELECT publication_id,email,image,description\n" +
                "FROM publications\n" +
                "INNER JOIN users on users.user_id = publications.user_id\n" +
                "WHERE publications.user_id = ?";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(PublicationDisplayDTO.class), userId);
    }

    public List<PublicationDisplayDTO> getNewsline(Long userId){
        String sql = "SELECT publication_id,username,image,description\n" +
                "FROM publications\n" +
                "INNER JOIN users on users.user_id = publications.user_id\n" +
                "WHERE publications.user_id = ANY (SELECT subscribedtoid\n" +
                "                     FROM subscriptions\n" +
                "                     WHERE subscribingid = ?)";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(PublicationDisplayDTO.class), userId);
    }

    public void delete(Long userId, Long publicationId){
        String sql = "DO $$\n" +
                "DECLARE\n" +
                "    userId BIGINT = " + userId + ";\n" +
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
