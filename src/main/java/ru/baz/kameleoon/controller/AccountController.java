package ru.baz.kameleoon.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.baz.kameleoon.dto.AccountDto;
import ru.baz.kameleoon.entity.Account;
import ru.baz.kameleoon.service.AccountService;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "Create new account", notes = "Returns created account")
    @PostMapping(value = "/account")
    @ResponseBody
    public AccountDto save(@RequestBody AccountDto accountDto) {
        return accountService.addUser(accountDto);
    }
}
