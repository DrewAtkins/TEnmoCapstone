package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface AccountDao {

    BigDecimal retrieveBalance(Long userId);


    //add to balance
   // BigDecimal addToBalance(BigDecimal amountToAdd, Long userId);

    //withdraw from balance
   // BigDecimal withdrawFromBalance(BigDecimal amountToWithdraw, Long userId);

    //should we use userId and accountId?? -- stick with account or user only.
}
