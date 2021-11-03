package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTransferDao  {
    private JdbcTemplate jdbcTemplate;

    //implements TransferDao

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //findBy Username?

    //display a list of users to send money to

    //
}
