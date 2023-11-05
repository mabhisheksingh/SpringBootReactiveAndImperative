package com.abhishek.nonreactive.controller;

import com.abhishek.utils.MyLoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static com.abhishek.utils.MyLoggerUtils.*;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
    private final Logger log = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/testController")
    public Mono<String> getTest() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(5000);
        log.info(INFO_FORMAT_START, HomeController.class.getName(), "getTest" , start);
        log.debug(INFO_FORMAT_START, HomeController.class.getName(), "getTest_DEBUG" , start);
        System.out.println("CONTROLLER ");

        log.trace("This is a TRACE level message");
        log.debug("This is a DEBUG level message");
        log.info("This is an INFO level message");
        log.warn("This is a WARN level message");
        log.error("This is an ERROR level message");

        Instant end = Instant.now();
        log.info(INFO_FORMAT_END, HomeController.class.getName(), "getTest" , end);
        log.info(TOTAL_TIME_FOR_EXECUTION,  (float)(end.getNano() -  start.getNano())/1_000_000 );

        return Mono.just("ABHSIHEK SINGH");
    }



    @GetMapping("/**")
    public String notFound(){
        return "Abhishek NOT FOUND";
    }
}
