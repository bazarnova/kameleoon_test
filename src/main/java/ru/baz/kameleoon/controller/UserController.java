package ru.baz.kameleoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.baz.kameleoon.entity.Account;
import ru.baz.kameleoon.service.AccountService;

@Controller
public class UserController {

    @Autowired
    private final AccountService accountService;

    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/user")
    @ResponseBody
    public Account addUser(@RequestBody Account account) {
        return accountService.addUser(account);
    }
}
