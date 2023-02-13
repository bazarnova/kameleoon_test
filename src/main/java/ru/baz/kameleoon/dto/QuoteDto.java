package ru.baz.kameleoon.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteDto {

    @ApiModelProperty(notes = "Quote id. When the save method is used, id = null", example = "1", required = false)
    private Long id;

    @ApiModelProperty(notes = "Content", example = "Hello! It is a nice day today, is not it ?", required = true)
    private String content;

    @ApiModelProperty(notes = "Account id", example = "1", required = true)
    private Long accountId;

    @ApiModelProperty(notes = "Score. When created is 0.", example = "0", required = false)
    private Integer score;
}
