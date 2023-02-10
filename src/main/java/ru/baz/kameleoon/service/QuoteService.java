package ru.baz.kameleoon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.baz.kameleoon.dto.QuoteDto;
import ru.baz.kameleoon.entity.Account;
import ru.baz.kameleoon.entity.Quote;
import ru.baz.kameleoon.repository.AccountRepository;
import ru.baz.kameleoon.repository.QuoteRepository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuoteService {
    @Autowired
    private final QuoteRepository quoteRepository;
    @Autowired
    private final AccountRepository accountRepository;

    public QuoteService(QuoteRepository quoteRepository, AccountRepository accountRepository) {
        this.quoteRepository = quoteRepository;
        this.accountRepository = accountRepository;
    }

    public QuoteDto save(@NotNull QuoteDto quoteDto) {
        Account account = accountRepository.findById(quoteDto.getAccountId()).get();
        Quote quote = Quote.builder()
                .content(quoteDto.getContent())
                .account(account)
                .score(quoteDto.getScore())
                .build();
        quoteRepository.save(quote);
        quoteDto.setId(quote.getId());
        return quoteDto;
    }

    public QuoteDto update(@NotNull QuoteDto quoteDto) {
        Quote quote = quoteRepository.findById(quoteDto.getId()).get();
        Account account = accountRepository.findById(quoteDto.getAccountId()).get();

        quote.setContent(quote.getContent());
        quote.setAccount(account);
        quote.setScore(quoteDto.getScore());
        quoteRepository.save(quote);
        return quoteDto;
    }

    public QuoteDto getById(@NotNull Long id) {
        Quote quote = quoteRepository.findById(id).get();

        return QuoteDto.builder()
                .id(quote.getId())
                .content(quote.getContent())
                .accountId(quote.getAccount().getId())
                .score(quote.getScore())
                .build();
    }

    public QuoteDto getRandom() {
        List<Quote> all = quoteRepository.findAll();
        if (all.size() >0){
            int random = new Random().nextInt(all.size());
            Quote quote = all.get(random);
            return QuoteDto.builder()
                    .id(quote.getId())
                    .content(quote.getContent())
                    .accountId(quote.getAccount().getId())
                    .score(quote.getScore())
                    .build();
        }
        return new QuoteDto();
    }

    public List<QuoteDto> getFlop(Long accountId) {
        Account account = accountRepository.findById(accountId).get();
        List<Quote> resultList = quoteRepository.findTop10ByAccountOrderByScoreAsc(account);
        List<QuoteDto> topList = new ArrayList<>();
        for (Quote quote : resultList) {
            topList.add(QuoteDto.builder()
                    .id(quote.getId())
                    .content(quote.getContent())
                    .accountId(quote.getAccount().getId())
                    .score(quote.getScore())
                    .build());
        }
        return topList;
    }
    public List<QuoteDto> getTop(Long id) {
        Account account = accountRepository.findById(id).get();
        List<Quote> resultList = quoteRepository.findTop10ByAccountOrderByScoreDesc(account);
        List<QuoteDto> topList = new ArrayList<>();
        for (Quote quote : resultList) {
            topList.add(QuoteDto.builder()
                    .id(quote.getId())
                    .content(quote.getContent())
                    .accountId(quote.getAccount().getId())
                    .score(quote.getScore())
                    .build());
        }
        return topList;
    }
    public boolean delete(@NotNull Long id) {
        return quoteRepository.deleteQuoteById(id) > 0;
    }
}
