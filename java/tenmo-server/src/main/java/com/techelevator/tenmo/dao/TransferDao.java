package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;

public interface TransferDao  {

    //send money

    BigDecimal sendMoney(Long accountFrom, Long accountTo, BigDecimal amount);
}
