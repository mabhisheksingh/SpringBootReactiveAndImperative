package com.abhishek.nonreactive.controller;

import com.abhishek.exceptions.ObjectNotFoundException;
import com.abhishek.model.Employee;
import com.abhishek.nonreactive.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static com.abhishek.utils.MyLoggerUtils.*;


@RestController
public class HomeController implements MainController{

    @Autowired
    HomeService homeService;

    private final Logger log = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/testController")
    public Mono<String> getTest() throws InterruptedException {
        Instant start = Instant.now();
        log.debug(INFO_FORMAT_START, HomeController.class.getName(), "getTest" , start);
        log.info(INFO_FORMAT_START, HomeController.class.getName(), "getTest_INFO" , start);
        Instant end = Instant.now();
        log.debug(INFO_FORMAT_END, HomeController.class.getName(), "getTest" , end);
        log.debug(TOTAL_TIME_FOR_EXECUTION,  (float)(end.getNano() -  start.getNano())/1_000_000 );
        return Mono.just("ABHSIHEK SINGH").delayElement(Duration.ofMillis(2000));
    }
    @GetMapping("/testException")
    public Mono<String> testException()  {

        return Mono.error(new ObjectNotFoundException("ABHSIHEK SINGH"));
    }
    @GetMapping("/getAll")
    public Mono<List<Employee>> getAllEmp()  {
        return homeService.getAllEmp();
    }
    @GetMapping("/getEmpById/{id}")
    public Mono<Employee> getAllEmp(@PathVariable Integer id)  {
        return homeService.getEmpById(id);
    }

    /**
     * Catch-all mapping for path not found
     */
//    @RequestMapping(value = "/**")
//    @ResponseBody
//    public String handlePathNotFound() {
//        // Handle the "path not found" scenario
//        return "Path not found";
//    }

}
