package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

    @PreAuthorize("isAuthenticated()")
    @RestController
    @RequestMapping("/tenmo")
    public class UserController {

        private UserDao userDao;


        public UserController(UserDao userDao) {
            this.userDao = userDao;
        }

        @RequestMapping(path = "/users", method = RequestMethod.GET)
        public List<User> listOfUsersForTransfer(Principal user) {
            List<User> userList =  userDao.listOfUsersForTransfer();
            return userList;
        }



    }
