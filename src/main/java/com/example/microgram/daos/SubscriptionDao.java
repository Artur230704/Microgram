package com.example.microgram.daos;

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
public class SubscriptionDao {
    private final JdbcTemplate jdbcTemplate;

    public String subscribe(Long userId, Long subscribedToId){
        String sql = "INSERT INTO subscriptions (subscribingid, subscribedtoid, subscription_date) " +
                "VALUES(?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1,userId);
                ps.setLong(2,subscribedToId);
                ps.setDate(3,Date.valueOf(LocalDate.now()));
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });

        return "You subscribed on user with id " + subscribedToId;
    }
}
