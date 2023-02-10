package ru.baz.kameleoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.baz.kameleoon.entity.Account;
import ru.baz.kameleoon.entity.Quote;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findTop10ByAccountOrderByScoreAsc(Account account);

    List<Quote> findTop10ByAccountOrderByScoreDesc(Account account);

    long deleteQuoteById(Long id);
}
