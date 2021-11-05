package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal retrieveBalance(String userName) throws UsernameNotFoundException {
        String sql = "SELECT balance " +
                "FROM users " +
                "JOIN accounts " +
                "ON users.user_id = accounts.user_id " +
                "WHERE username = ?;";
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userName);
        return balance;
    }

    @Override
    public Account findAccountByUsername(String userName) throws UsernameNotFoundException {
        String sql = "SELECT account_id, users.user_id, balance" +
                " FROM users JOIN accounts ON users.user_id = accounts.user_id WHERE username = ?;";
        Account account = null;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
              if (results.next()) {
                  account = mapRowToAccount(results);
              }
        return account;
    }

   // does this need to be mapped?? VVVV what is his purpose - "helper method for our sendMonday mapping" //TODO ask dave
    @Override
   public Long getAccountIdByUsername(String userName){
        String sql = "SELECT account_id FROM users JOIN accounts ON users.user_id = accounts.user_id" +
                " WHERE username = ?;";
        Long accountid = jdbcTemplate.queryForObject(sql, Long.class, userName);
        return accountid;
    }

    private Account mapRowToAccount(SqlRowSet rowSet){
        Account account = new Account();
        account.setAccountId(rowSet.getLong("account_id"));
        account.setUserId(rowSet.getLong("user_id"));
        account.setBalance(rowSet.getBigDecimal("balance"));
        return account;
    }
}

//TODO ask dave, how to implement methods on the client side