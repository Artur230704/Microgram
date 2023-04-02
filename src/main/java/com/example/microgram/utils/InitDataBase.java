package com.example.microgram.utils;


import com.example.microgram.daos.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
@AllArgsConstructor
public class InitDataBase {
    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;
    private PasswordEncoder encoder;

    @Bean
    public void createTables(){
        createUsersTable();
        createPublicationsTable();
        createCommentsTable();
        createLikesTable();
        createSubscriptionsTable();
    }

    @Bean
    public void fillTables(){
        fillUsersTable();
        fillPublicationsTable();
        fillCommentsTable();
        fillLikesTable();
        fillSubscriptionsTable();
    }


    private void createUsersTable(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(\n" +
                "    user_id BIGSERIAL PRIMARY KEY,\n" +
                "    username TEXT,\n" +
                "    email TEXT,\n" +
                "    user_password TEXT,\n" +
                "    user_role TEXT,\n" +
                "    enabled BOOLEAN,\n" +
                "    publications INTEGER,\n" +
                "    subscriptions INTEGER,\n" +
                "    subscribers INTEGER \n" +
                ")");
    }
    private void createPublicationsTable(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS publications(\n" +
                "    publication_id BIGSERIAL PRIMARY KEY,\n" +
                "    user_id BIGINT,\n" +
                "    image TEXT,\n" +
                "    description TEXT,\n" +
                "    publication_date DATE,\n" +
                "    FOREIGN KEY (user_id) REFERENCES users(user_id)\n" +
                ");");
    }
    private void createCommentsTable(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS comments(\n" +
                "    comment_id BIGSERIAL PRIMARY KEY,\n" +
                "    user_id BIGINT,\n" +
                "    publication_id BIGINT,\n" +
                "    comment_text TEXT,\n" +
                "    comment_date DATE,\n" +
                "    FOREIGN KEY (user_id) REFERENCES users(user_id),\n" +
                "    FOREIGN KEY (publication_id) REFERENCES publications(publication_id)\n" +
                ");");
    }
    private void createLikesTable(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS likes(\n" +
                "    like_id BIGSERIAL PRIMARY KEY,\n" +
                "    user_id BIGINT,\n" +
                "    object_type TEXT,\n" +
                "    publication_id BIGINT,\n" +
                "    comment_id BIGINT,\n" +
                "    like_date DATE,\n" +
                "    FOREIGN KEY (publication_id) REFERENCES publications(publication_id),\n" +
                "    FOREIGN KEY (comment_id) REFERENCES comments(comment_id)\n" +
                ");");
    }
    private void createSubscriptionsTable(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS subscriptions(\n" +
                "    subscription_id BIGSERIAL PRIMARY KEY,\n" +
                "    subscribingId BIGINT,\n" +
                "    subscribedToId BIGINT,\n" +
                "    subscription_date DATE,\n" +
                "    FOREIGN KEY (subscribingId) REFERENCES users(user_id),\n" +
                "    FOREIGN KEY (subscribedToId) REFERENCES users(user_id)\n" +
                ");\n");
    }


    private void fillUsersTable(){
        String sql = "INSERT INTO users(username, email, user_password, user_role, enabled, publications, subscriptions, subscribers)\n" +
                "VALUES\n" +
                "    ('Artur','artur230704@gmail.com',?,'USER',true,0,2,0),\n" +
                "    ('Andrey','andrey@gmail.com',?,'USER',true,2,1,2),\n" +
                "    ('Petya','Petya@gmail.com',?,'USER',true,1,1,2);";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,encoder.encode("qwerty"));
                ps.setString(2,encoder.encode("asd"));
                ps.setString(3,encoder.encode("zxc"));
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });
    }
    private void fillPublicationsTable(){
        jdbcTemplate.execute("INSERT INTO publications(user_id, image, description, publication_date)\n" +
                "VALUES\n" +
                "    (2,'none','my first publication','2023-01-01'),\n" +
                "    (2,'none','my second publication','2023-02-02'),\n" +
                "    (3,'none','my blog','2023-03-03');\n");
    }
    private void fillCommentsTable(){
        jdbcTemplate.execute("INSERT INTO comments(user_id, publication_id, comment_text, comment_date)\n" +
                "VALUES\n" +
                "    (1,1,'Cool!','2023-01-02'),\n" +
                "    (2,3,'good blog','2023-03-03'),\n" +
                "    (1,3,'interesting','2023-03-05');\n");
    }
    private void fillLikesTable(){
        jdbcTemplate.execute("INSERT INTO likes(user_id, object_type, publication_id, comment_id, like_date)\n" +
                "VALUES\n" +
                "    (1,'Publication',1,null,'2023-01-02'),\n" +
                "    (1,'Comment',null,2,'2023-03-05'),\n" +
                "    (2,'Publication',3,null,'2023-03-03'),\n" +
                "    (1,'Publication',3,null,'2023-03-05'),\n" +
                "    (3,'Comment',null,2,'2023-03-03');\n");
    }
    private void fillSubscriptionsTable(){
        jdbcTemplate.execute("INSERT INTO subscriptions(subscribingId, subscribedToId, subscription_date)\n" +
                "VALUES\n" +
                "    (1,2,'2023-01-10'),\n" +
                "    (1,3,'2022-12-18'),\n" +
                "    (2,3,'2023-02-26'),\n" +
                "    (3,2,'2023-03-04')");
    }
}
