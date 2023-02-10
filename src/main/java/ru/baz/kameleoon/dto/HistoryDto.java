package ru.baz.kameleoon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryDto {
    private Long id;
    private Long accountId;
    private Long quoteId;
    private Integer vote;
    @Nullable
    private Date votingTime;
}
