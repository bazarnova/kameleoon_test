package ru.baz.kameleoon.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.baz.kameleoon.dto.QuoteDto;
import ru.baz.kameleoon.service.QuoteService;

import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @ApiOperation(value = "Create new quote", notes = "Returns created quote")
    @PostMapping(value = "/quote")
    @ResponseBody
    public QuoteDto save(@RequestBody QuoteDto quoteDto) {
        return quoteService.save(quoteDto);
    }

    @ApiOperation(value = "Update the quote", notes = "Returns updated quote")
    @PutMapping(value = "/quote")
    @ResponseBody
    public QuoteDto update(@RequestBody QuoteDto quoteDto) {
        return quoteService.update(quoteDto);
    }

    @ApiOperation(value = "Get a quote by id", notes = "Returns a quote by id")
    @GetMapping(value = "/quote/{id}")
    @ResponseBody
    public QuoteDto getById(@PathVariable Long id) {
        return quoteService.getById(id);
    }

    @ApiOperation(value = "Get a random quote", notes = "Returns a random quote")
    @GetMapping(value = "/quote/any")
    @ResponseBody
    public QuoteDto getRandom() {
        return quoteService.getRandom();
    }

    @ApiOperation(value = "Get the top ten quotes by accountId", notes = "Get the top ten quotes of the specified account")
    @GetMapping(value = "/quote/top/{account_id}")
    @ResponseBody
    public List<QuoteDto> getTop10ByAccountId(@PathVariable("account_id") Long accountId) {
        return quoteService.getTop(accountId);
    }

    @ApiOperation(value = "Get the flop ten quotes by accountId", notes = "Get the flop ten quotes of the specified account")
    @GetMapping(value = "/quote/flop/{account_id}")
    @ResponseBody
    public List<QuoteDto> getFlop10ByAccountId(@PathVariable("account_id") Long accountId) {
        return quoteService.getFlop(accountId);
    }

    @ApiOperation(value = "Remove quote by id", notes = "Returns true if the quote is removed")
    @DeleteMapping(value = "/quote/{id}")
    @ResponseBody
    public boolean delete(@PathVariable Long id) {
        return quoteService.delete(id);
    }
}
