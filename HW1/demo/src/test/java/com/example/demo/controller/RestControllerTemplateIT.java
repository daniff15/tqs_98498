package com.example.demo.controller;

import com.example.demo.entities.ByParams;
import com.example.demo.repository.CovidRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class RestControllerTemplateIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CovidRepository covidRepository;

    @ParameterizedTest
    @ValueSource(strings = {"/date/2023-07-07", "/date/2032423-07-07", "/country/dont_exist", "/date/2023-07-07/country/Portugal", "/date/2435023-07-07/country/Portugal", "/date/2020-07-07/country/dont_exist"})
    void badTests_withBadInputs(String arg) {
        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                getBaseUrl() + arg,
                ByParams.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testRequestCountry_thenStatus200() {
        ByParams byParams = createTestClass();

        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                "/api/country/" + byParams.getCountry(),
                ByParams.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).extracting(ByParams::getCountry).isEqualTo(byParams.getCountry());

    }

    @Test
    void testRequestDate_thenStatus200() {
        ByParams byParams = createTestClass();

        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                "/api/date/" + byParams.getDate(),
                ByParams.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).extracting(ByParams::getDate).isEqualTo(byParams.getDate());

    }

    @Test
    void testRequestParams_thenStatus200() {
        ByParams byParams = createTestClass();

        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                "/api/date/" + byParams.getDate() + "/country/" + byParams.getCountry(),
                ByParams.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).extracting(ByParams::getDate).isEqualTo(byParams.getDate());
        assertThat(response.getBody()).extracting(ByParams::getCountry).isEqualTo(byParams.getCountry());

    }

    private ByParams createTestClass() {
        ByParams byParams = new ByParams();
        byParams.setActive(500);
        byParams.setConfirmed(1000000);
        byParams.setRecovered(900000);
        byParams.setDeaths(50000);
        byParams.setCountry("Portugal");
        byParams.setConfirmedDiff(1500);
        byParams.setActiveDiff(-15);
        byParams.setDeathsDiff(12);
        byParams.setRecoveredDiff(3500);
        byParams.setFatalityRate(0.035);
        byParams.setDate("2020-07-20");
        byParams.setLastUpdated((new Date(System.currentTimeMillis()).toString()));

        return byParams;
    }

    public String getBaseUrl() {
        return "http://localhost:" + randomServerPort + "/api";

    }
}
