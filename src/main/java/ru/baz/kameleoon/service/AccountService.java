package ru.baz.kameleoon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.baz.kameleoon.entity.Account;
import ru.baz.kameleoon.repository.AccountRepository;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public Account addUser(Account account){
        return accountRepository.save(account);
    }
}
