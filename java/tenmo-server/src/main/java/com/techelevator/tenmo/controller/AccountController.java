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
import java.security.Principal;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/tenmo")

public class AccountController {
    private AccountDao accountDao;

    public AccountController(AccountDao accountDao){
        this.accountDao = accountDao;

    }

    //TODO fix exceptions
    @RequestMapping(path ="/balance", method = RequestMethod.GET)
    public BigDecimal retrieveBalance(Principal user) throws UsernameNotFoundException {
        BigDecimal balance = accountDao.retrieveBalance(user.getName());
        return balance;
    }

    @RequestMapping(path ="/account", method = RequestMethod.GET)
    public Account findAccountByUsername(Principal user) throws UsernameNotFoundException {
        Account account = accountDao.findAccountByUsername(user.getName());
        return account;
    }

    //


}

