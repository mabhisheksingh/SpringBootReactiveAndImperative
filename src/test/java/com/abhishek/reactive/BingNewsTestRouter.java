package com.abhishek.reactive;

import com.abhishek.model.Employee;
import com.abhishek.nonreactive.service.HomeService;
import com.abhishek.reactive.handlers.BingNewsHandler;
import com.abhishek.reactive.routers.BingNewsRouter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Tag(value = "BingNews")
@ComponentScan(basePackages = "com.abhishek.reactive")
public class BingNewsTestRouter {
    private static final String BASE_PATH = "/api/v1";

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @Disabled
    void testLoadedBeans() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            System.out.println("Loaded Bean: " + beanName);
        }
    }

    @Test
    @DisplayName("get Bing news by category")
    @Tag(value = "EMP")
    void getBingNewsByCategory(){
        //Mockito.when(homeService.getBingNewsByCategory()).thenReturn(Mono.just(employeeList));
        webTestClient
                .mutateWith(SecurityMockServerConfigurers.mockUser().password("user").roles("USER"))
                .get()
                .uri(BASE_PATH + "/bing-news")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)

                .expectBody()
                .jsonPath("$.webSearchUrl").isEqualTo("https://www.bing.com/news/search?q=top+stories&form=TNSA02")
                .jsonPath("$._type").isEqualTo("News");

    }
}
