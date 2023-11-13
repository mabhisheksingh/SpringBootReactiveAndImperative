package com.abhishek.controllers;

import com.abhishek.model.Employee;
import com.abhishek.nonreactive.controller.HomeController;
import com.abhishek.nonreactive.service.HomeService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
/**
 * Integration Testing

 * @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT);
 * @AutoConfigureWebTestClient
 * We Should not use SpringBootTest in all class because it starts all Spring boot context menus bootstrap
 * all beans that is not a good choice for slice testing
 * Prefer to use test-containers because it gives light weight of your required prod db in test environment
 */
//@ExtendWith(SpringExtension.class)
//@WebFluxTest(HomeController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Tag(value = "HomeController")
public class HomeControllerTest {
    private static final String BASE_PATH = "/api/v1";
    private List<Employee> employeeList = Arrays.asList(
            new Employee(1,"Abhishek Singh")    ,
            new Employee(2,"Ashish Singh") ,
            new Employee(3,"Shivam Singh")  ,
            new Employee(4,"test Singh")
    );

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    HomeService homeService;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @Disabled
    void testLoadedBeans() {
        //use to print what all beans loaded in test execution of this class
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("Loaded Bean: " + beanName);
        }
    }
    @Test
    @DisplayName("get All employee.")
    @Tag(value = "EMP")
    void getAllEmp(){
        Mockito.when(homeService.getAllEmp()).thenReturn(Mono.just(employeeList));
        webTestClient
                .mutateWith(SecurityMockServerConfigurers.mockUser().password("user").roles("USER"))
                .get()
                .uri(BASE_PATH + "/getAll")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Employee.class)
                .isEqualTo(employeeList);

    }
    @Test
    @DisplayName("get emp by id")
    @Tag(value = "EMP")
    void getEmpById(){
        Mockito.when(homeService.getEmpById(1)).thenReturn(Mono.just(
                employeeList.stream().filter(emp ->emp.getId() == 1).findFirst().get()
        ));
        webTestClient
                .mutateWith(SecurityMockServerConfigurers.mockUser().password("user").roles("USER"))
                .get()
                .uri(BASE_PATH + "/getEmpById/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1);
    }

    @Test
    @DisplayName("test controller")
    @Tag(value = "TEST")
    void testController(){
        webTestClient
                .mutateWith(SecurityMockServerConfigurers.mockUser().password("user").roles("USER"))
                .get()
                .uri(BASE_PATH + "/testController")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class)
                .isEqualTo("ABHSIHEK SINGH");
    }

}
