package ru.baz.kameleoon;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import ru.baz.kameleoon.dto.HistoryDto;
import ru.baz.kameleoon.entity.Account;
import ru.baz.kameleoon.entity.History;
import ru.baz.kameleoon.entity.Quote;
import ru.baz.kameleoon.repository.AccountRepository;
import ru.baz.kameleoon.repository.QuoteRepository;

import java.net.URI;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = KameleoonTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class KameleoonTestApplicationTests {

    private static final int NUMBER_OF_THREADS = 10;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;


    @Test
    void testRequestForUpdateConcurrency() throws InterruptedException {
        Account account = new Account(null,"Name","e@mail", "pass", new Date());
        Account savedAccount = accountRepository.save(account);

        Quote quote = Quote.builder()
                .account(savedAccount)
                .content("some text")
                .score(0)
                .build();
        quoteRepository.save(quote);

        ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            int finalI = i;
            Runnable runnable = () -> {
                RestTemplate restTemplate = new RestTemplate();
                HistoryDto historyDto = HistoryDto.builder()
                        .id(1L)
                        .accountId(1L)
                        .quoteId(1L)
                        .vote(1)
                        .build();
                RequestEntity<HistoryDto> requestEntity = new RequestEntity<>(historyDto, HttpMethod.POST,
                        URI.create("http://localhost:" + webServerAppCtxt.getWebServer().getPort() + "/history"));
                ResponseEntity<History> response = restTemplate.exchange(requestEntity, History.class);
                log.debug("response {} {}", finalI, response.getBody());
                latch.countDown();
            };
            service.submit(runnable);

        }
        latch.await();
        Quote resultQuote = quoteRepository.findById(1L).get();
        assertEquals(quote.getScore() + NUMBER_OF_THREADS, resultQuote.getScore());

    }

    private Runnable getTask(CountDownLatch latch, final int finalI) {
        return () -> {
            RestTemplate restTemplate = new RestTemplate();
            HistoryDto historyDto = HistoryDto.builder()
                    .id(1L)
                    .accountId(1L)
                    .quoteId(1L)
                    .vote(1)
                    .build();
            RequestEntity<HistoryDto> requestEntity = new RequestEntity<>(historyDto, HttpMethod.POST,
                    URI.create("http://localhost:" + webServerAppCtxt.getWebServer().getPort() + "/"));
            ResponseEntity<History> response = restTemplate.exchange(requestEntity, History.class);
            log.debug("response {} {}", finalI, response.getBody());
            latch.countDown();
        };
    }
}
