package ru.baz.kameleoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.baz.kameleoon.entity.Account;
import ru.baz.kameleoon.entity.History;
import ru.baz.kameleoon.entity.Quote;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> getHistoriesByQuoteAndVotingTimeLessThanEqualAndVotingTimeGreaterThanEqual(Quote quote,
                                                                                                 Date endDate,
                                                                                                 Date startDate );
}
