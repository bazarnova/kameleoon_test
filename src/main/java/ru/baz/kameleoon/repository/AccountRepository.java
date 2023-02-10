package ru.baz.kameleoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.baz.kameleoon.entity.Account;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {
}
