package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao  {

    void sendMoney(Long accountFrom, Long accountTo, BigDecimal amount);
    //void requestMoney(Long accountFrom, Long accountTo, BigDecimal amount);
    void addToRecipient(BigDecimal amount, Long userId);
    void subtractFromSender(BigDecimal amount, Long userId);
    List<Transfer> getTransfers(Long userId);


}
