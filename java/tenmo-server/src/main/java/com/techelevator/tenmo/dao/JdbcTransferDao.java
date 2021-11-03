package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    //implements TransferDao

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

   @Override
    public void sendMoney(Long accountFrom, Long accountTo, BigDecimal amount)  {
    String sql = ("INSERT INTO transfers(transfer_type_id, transfer_status_id, account_from, account_to, amount " +
            "VALUES(2,2,?,?,?) RETURNING transfer_id;");
     jdbcTemplate.update(sql, String.class, accountFrom, accountTo, amount);

     //add to account to
    addToRecipient(amount, accountTo);
     //subtract from account from
    subtractFromSender(amount, accountFrom);
    }

    @Override
    public void addToRecipient(BigDecimal amount, Long userId) {
    String sql = ("UPDATE accounts SET balance = balance + ? WHERE user_id = ?;");
    jdbcTemplate.update(sql, amount, userId);
    }

    @Override
    public void subtractFromSender(BigDecimal amount, Long userId) {
    String sql = ("UPDATE accounts SET balance = balance - ? WHERE user_id = ?;");
    jdbcTemplate.update(sql, amount, userId);
    }

    @Override
    public List<Transfer> getTransfers(Long userId) {
        List<Transfer> transferList = new ArrayList<>();
        String sql = ("SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                " FROM transfers JOIN accounts ON transfers.account_from = accounts.account_id WHERE user_id = ?;");

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, String.class, userId);
        while (results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transferList.add(transfer);
        }
        return transferList;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getLong("transfer_id"));
        transfer.setTransferTypeId(rs.getLong("transfer_type_id"));
        transfer.setTransferStatusId(rs.getLong("transfer_status_id"));
        transfer.setAccountFrom(rs.getLong("account_from"));
        transfer.setAccountTo(rs.getLong("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
    }

    //findBy Username?

    //display a list of users to send money to

    /* BigDecimal transferAmount = new BigDecimal(50.00);
        if (balance.compareTo(transferAmount) >= 0) {
          balance = balance.subtract(transferAmount);
            return balance;
        }
     */
}
