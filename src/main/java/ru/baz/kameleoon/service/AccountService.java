package ru.baz.kameleoon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.baz.kameleoon.entity.Account;
import ru.baz.kameleoon.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account addUser(Account account){
        return accountRepository.save(account);
    }
}
