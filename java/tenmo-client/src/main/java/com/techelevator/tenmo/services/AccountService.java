package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import okhttp3.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class AccountService {

    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private User user;


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
//    public Transfer sendMoney(AuthenticatedUser user, Long accountTo, BigDecimal amount) {
//       BigDecimal amountToSend = new BigDecimal(0.00);
//        ResponseEntity<BigDecimal> responseEntity = restTemplate.exchange(baseUrl + "tenmo/transfer", HttpMethod.POST, makeAuthEntity(user), BigDecimal.class);
//       amountToSend = responseEntity.getBody();
//       return amountToSend;
   // }


}
