package ru.baz.kameleoon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteDto {
    private Long id;
    private String content;
    private Long accountId;
    private Integer score;
}
