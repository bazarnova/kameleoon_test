package ru.baz.kameleoon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.baz.kameleoon.entity.Account;
import ru.baz.kameleoon.service.AccountService;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final AccountService accountService;

    @PostMapping(value = "/user")
    @ResponseBody
    public Account addUser(@RequestBody Account account) {
        return accountService.addUser(account);
    }
}
