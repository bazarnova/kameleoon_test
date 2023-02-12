package ru.baz.kameleoon.dto;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "History id. When the save method is used, id = null", example = "1", required = false)
    private Long id;

    @ApiModelProperty(notes = "Account id", example = "1", required = true)
    private Long accountId;

    @ApiModelProperty(notes = "Quote id", example = "1", required = true)
    private Long quoteId;

    @ApiModelProperty(notes = "Vote", example = "1 or -1", required = true)
    private Integer vote;

    @ApiModelProperty(notes = "Date of voting", required = false)
    @Nullable
    private Date votingTime;
}
