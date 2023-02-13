package ru.baz.kameleoon.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @ApiModelProperty(notes = "Account id. When the save method is used, id = null", example = "1", required = false)
    private Long id;

    @ApiModelProperty(notes = "User name", example = "Alex", required = true)
    private String name;

    @ApiModelProperty(notes = "User email", example = "alex@gmail.com", required = true)
    private String email;

    @ApiModelProperty(notes = "User password", example = "123", required = true)
    private String password;

    @ApiModelProperty(notes = "Creation date", required = false)
    private Date creationDate;
}
