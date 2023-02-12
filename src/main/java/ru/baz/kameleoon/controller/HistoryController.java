package ru.baz.kameleoon.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.baz.kameleoon.dto.HistoryDto;
import ru.baz.kameleoon.service.HistoryService;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HistoryController {

    private final HistoryService historyService;

    @ApiOperation(value = "Save voting history and update current quote", notes = "Returns created history")
    @PostMapping(value = "/history")
    @ResponseBody
    public HistoryDto save(@RequestBody HistoryDto historyDto) {
        return historyService.save(historyDto);
    }

    @ApiOperation(value = "Get a history by id", notes = "Returns a history by id")
    @GetMapping(value = "/history/{id}")
    @ResponseBody
    public HistoryDto getById(@PathVariable("id") Long id) {
        return historyService.getById(id);
    }

    @ApiOperation(value = "Get a graph by id in the specified period", notes = "Returns a graph")
    @GetMapping(value = "/history")
    @ResponseBody
    public List<HistoryDto> getGraph(@RequestParam("quote_id") Long quoteId,
                                     @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                     @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return historyService.getGraph(quoteId, startDate, endDate);
    }
}
