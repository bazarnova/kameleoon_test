package ru.baz.kameleoon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.baz.kameleoon.dto.AccountDto;
import ru.baz.kameleoon.entity.Account;
import ru.baz.kameleoon.repository.AccountRepository;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountDto addUser(AccountDto accountDto) {
        Account account = Account.builder()
                .name(accountDto.getName())
                .password(accountDto.getPassword())
                .email(accountDto.getEmail())
                .build();
        accountRepository.save(account);
        accountDto.setId(account.getId());
        accountDto.setCreationDate(account.getCreationDate());
        return accountDto;
    }
}
