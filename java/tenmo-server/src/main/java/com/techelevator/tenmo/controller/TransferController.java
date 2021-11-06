package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/tenmo")

public class TransferController {
    private TransferDao transferDao;
    private AccountDao accountDao;

    public TransferController(TransferDao transferDao, AccountDao accountDao){
        this.transferDao = transferDao;
        this.accountDao = accountDao;
    }



    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public Transfer sendMoney(@RequestBody Transfer transfer, Principal user){
       transferDao.sendMoney(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
      return transfer;
    }

    @RequestMapping (path = "/transfer", method = RequestMethod.GET)
    public List<Transfer> getTransfers(Principal user){
        Account account = accountDao.findAccountByUsername(user.getName());
        return transferDao.getTransfers(account.getUserId());
    }





}
