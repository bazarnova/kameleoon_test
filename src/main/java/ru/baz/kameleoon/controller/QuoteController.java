package ru.baz.kameleoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.baz.kameleoon.dto.QuoteDto;
import ru.baz.kameleoon.service.QuoteService;

import java.util.List;

@Controller
@Transactional
public class QuoteController {
    @Autowired
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping(value = "/quote")
    @ResponseBody
    public QuoteDto addQuote(@RequestBody QuoteDto quoteDto) {
        return quoteService.save(quoteDto);
    }

    @PutMapping(value = "/quote")
    @ResponseBody
    public QuoteDto updateQuote(@RequestBody QuoteDto quoteDto) {
        return quoteService.update(quoteDto);
    }

    @GetMapping(value = "/quote/{id}")
    @ResponseBody
    public QuoteDto getQuote(@PathVariable Long id) {
        return quoteService.getById(id);
    }

    @GetMapping(value = "/quote/any")
    @ResponseBody
    public QuoteDto getRandomQuote() {
        return quoteService.getRandom();
    }

    @GetMapping(value = "/quote/top/{account_id}")
    @ResponseBody
    public List<QuoteDto> getTopQuotes(@PathVariable("account_id") Long accountId) {
        return quoteService.getTop(accountId);
    }

    @GetMapping(value = "/quote/flop/{id}")
    @ResponseBody
    public List<QuoteDto> getFlopQuotes(@PathVariable Long id) {
        return quoteService.getFlop(id);
    }

    @DeleteMapping(value = "/quote/{id}")
    @ResponseBody
    public boolean deleteQuote(@PathVariable Long id) {
        return quoteService.delete(id);
    }
}
