package ru.baz.kameleoon.service;

import com.zaxxer.hikari.util.IsolationLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baz.kameleoon.dto.HistoryDto;
import ru.baz.kameleoon.entity.Account;
import ru.baz.kameleoon.entity.History;
import ru.baz.kameleoon.entity.Quote;
import ru.baz.kameleoon.repository.AccountRepository;
import ru.baz.kameleoon.repository.HistoryRepository;
import ru.baz.kameleoon.repository.QuoteRepository;

import javax.persistence.LockModeType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryService {

  private final HistoryRepository historyRepository;

    private final AccountRepository accountRepository;

    private final QuoteRepository quoteRepository;

//    @Transactional
    @Lock(LockModeType.PESSIMISTIC_READ)
    public HistoryDto save(HistoryDto historyDto) {
        Account account = accountRepository.findById(historyDto.getAccountId()).get();
        Quote quote = quoteRepository.findById(historyDto.getQuoteId()).get();

        History history = History.builder()
                .account(account)
                .quote(quote)
                .vote(historyDto.getVote())
                .build();
        historyRepository.save(history);
        historyDto.setId(quote.getId());

        quote.setScore(quote.getScore() + history.getVote());
        quoteRepository.save(quote);

        return historyDto;
    }

    public HistoryDto getById(Long id) {
        History history = historyRepository.findById(id).get();
        return HistoryDto.builder()
                .id(history.getId())
                .accountId(history.getAccount().getId())
                .quoteId(history.getQuote().getId())
                .votingTime(history.getVotingTime())
                .build();
    }


    public List<HistoryDto> getGraph(Long quoteId, Date startDate, Date endDate) {
        Quote quote = quoteRepository.findById(quoteId).get();
        List<History> histories =
                historyRepository.getHistoriesByQuoteAndVotingTimeLessThanEqualAndVotingTimeGreaterThanEqual(quote, endDate, startDate);
        List<HistoryDto> historyDtoList = new ArrayList<>();
        for (History history : histories) {
            historyDtoList.add(HistoryDto.builder()
                    .id(history.getId())
                    .accountId(history.getAccount().getId())
                    .vote(history.getVote())
                    .quoteId(history.getQuote().getId())
                    .votingTime(history.getVotingTime())
                    .build());
        }
        return historyDtoList;
    }
}
