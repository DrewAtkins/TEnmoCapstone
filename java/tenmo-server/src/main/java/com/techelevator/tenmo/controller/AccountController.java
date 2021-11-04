package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/tenmo")

public class AccountController {
    private AccountDao accountDao;

    public AccountController(AccountDao accountDao){
        this.accountDao = accountDao;

    }

    @RequestMapping(path ="/balance/{id}", method = RequestMethod.GET)
    public BigDecimal retrieveBalance(@PathVariable Long userId) throws UsernameNotFoundException {
        BigDecimal balance = accountDao.retrieveBalance(userId);
        return balance;
    }

    @RequestMapping(path ="/account/{id}", method = RequestMethod.GET)
    public Account findAccountById(@PathVariable Long userId) throws UsernameNotFoundException {
        Account account = accountDao.findAccountById(userId);
        return account;
    }
}

