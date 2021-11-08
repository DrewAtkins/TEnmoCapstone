package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import okhttp3.Response;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class AccountService {

    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
   // private User user;
    private Transfer transfer;


    public AccountService(String url) {
        this.baseUrl = url;
    }

    // this can be named anything VVV name is used for reference
    public BigDecimal retrieveBalance(AuthenticatedUser user) {
        BigDecimal balance = new BigDecimal(0.00);
        ResponseEntity<BigDecimal> response =
                restTemplate.exchange(baseUrl + "tenmo/balance", HttpMethod.GET, makeAuthEntity(user), BigDecimal.class);
        balance = response.getBody();
        return balance;
    }

    //getaccIdfromUserId - method //TODO

    //this is setting our token VVV help method
    private HttpEntity<Void> makeAuthEntity(AuthenticatedUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(user.getToken());
        return new HttpEntity<>(headers);

    }

    public Transfer[] getTransfers(AuthenticatedUser user) {
        Transfer[] transfers = null;
        ResponseEntity<Transfer[]> response = restTemplate.exchange(baseUrl + "tenmo/transfer", HttpMethod.GET, makeAuthEntity(user), Transfer[].class);
        transfers = response.getBody();
        return transfers;
    }

    //TODO Work on this
    public void sendMoney(AuthenticatedUser user, Long accountTo, BigDecimal amount) {
        Transfer transfer = new Transfer();
        transfer.setAccountTo(accountTo);
        transfer.setAmount(amount);
        ResponseEntity<Transfer> responseEntity = restTemplate.exchange(baseUrl + "tenmo/transfer",
                HttpMethod.POST, makeTransferEntity(user, transfer), Transfer.class); //<-- has to do with what we are expecting to get back from post to Server, not the method?
      // amountToSend = responseEntity.getBody();
    }

    public User[] listOfUsersForTransfer(AuthenticatedUser user) {
        User[] users = null;
        ResponseEntity<User[]> response = restTemplate.exchange(baseUrl + "tenmo/users", HttpMethod.GET, makeAuthEntity(user), User[].class);
        users = response.getBody();
        return users;
    }


    private HttpEntity<Transfer> makeTransferEntity(AuthenticatedUser user, Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(user.getToken());
        return new HttpEntity<Transfer>(transfer, headers);

    }
}
