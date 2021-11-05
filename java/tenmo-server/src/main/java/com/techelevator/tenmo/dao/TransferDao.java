package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao  {

    void sendMoney(Long accountFrom, Long accountTo, BigDecimal amount);
    //void requestMoney(Long accountFrom, Long accountTo, BigDecimal amount);
    void addToRecipient(BigDecimal amount, Long accountToId);
    void subtractFromSender(BigDecimal amount, Long accountFromId);
    List<Transfer> getTransfers(Long userId);


}
