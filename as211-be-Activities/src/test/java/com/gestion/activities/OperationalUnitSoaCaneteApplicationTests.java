package com.gestion.activities;

import com.gestion.activities.domain.model.Activities;
import com.gestion.activities.repository.ActivitiesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import java.util.List;



@SpringBootTest
@AutoConfigureWebTestClient
class OperationalUnitSoaCaneteApplicationTests {
    @Autowired
    ActivitiesRepository activitiesRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateOperationalUnit() {
        Activities activities = new Activities(
                "testeoName",
                "testeoDirector",
                2023-10-10,
                "2 meses",
                "testeoDepartment",
                "A",
                "testeoTypeP",
                "testeoTypeS");

        webTestClient.post()
                .uri("/ms-soa")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(activities), Activities.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Activities.class)
                .value(Activities::getId, notNullValue())
                .value(Activities::getName, equalTo("testeoName"))
                .value(Activities::getDescription, equalTo("testeoDirector"))
                .value(Activities::getDate, equalTo(2023-10-10))
                .value(Activities::getDuration, equalTo("2 meses"))
                .value(Activities::getLocation, equalTo("testeoDepartment"))
                .value(Activities::getActive, equalTo("A"))
                .value(Activities::getActive, equalTo("testeoTypeP"))
                .value(Activities::getActive, equalTo("testeoTypeS"));

    }

    @Test
    public void testListOperatinalUnit() {
        webTestClient.get().uri("/ms-soa/listData")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Activities.class)
                .consumeWith(response -> {
                    List<Activities> dataList = response.getResponseBody();
                    Assertions.assertTrue(dataList != null && !dataList.isEmpty(), "La lista no debe de estar vacía: ");
                    Assertions.assertFalse(false, "La lista debe contener al menos " + 1 + " elementos");
                    System.out.println(dataList);
                });


    }

    @Test
    public void testListInactiveLegalGuardian() {
        webTestClient.get().uri("/ms-soa/listData/inactive")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Activities.class)
                .consumeWith(response -> {
                    List<Activities> dataList = response.getResponseBody();
                    Assertions.assertTrue(dataList != null && !dataList.isEmpty(), "La lista no debe de estar vacía: ");
                    Assertions.assertTrue(true, "La lista debe contener al menos " + 1 + " elementos");
                    System.out.println(dataList);
                });

    }

    @Test
    public void testListActiveLegalGuardian() {
        webTestClient.get().uri("/ms-soa/listData/active")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Activities.class)
                .consumeWith(response -> {
                    List<Activities> dataList = response.getResponseBody();
                    Assertions.assertTrue(dataList != null && !dataList.isEmpty(), "La lista no debe de estar vacía: ");
                    Assertions.assertTrue(true, "La lista debe contener al menos " + 1 + " elementos");
                    System.out.println(dataList);
                });

    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    public void testUpdateFuncionary(int dataId) {
        Activities UpdateActivities = new Activities(
                "testeoNameUpdate",
                "testeoDirectorUpdate",
                2023-12-13,
                "testeoAdressUpdate",
                "2 meses",
                "A",
                "testeoTypeP",
                "testeoTypeS");

        webTestClient.put().uri("/ms-soa/{id}", dataId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(UpdateActivities)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Activities.class)
                .consumeWith(response -> {
                });
    }
}
