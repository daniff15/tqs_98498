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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class RestControllerTemplateIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CovidRepository covidRepository;

    @Test
    public void testAdvancedDate_thenBadRequest() {
        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                getBaseUrl() + "/date/2023-07-07",
                ByParams.class);

        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testBadFormatDate_thenBadRequest() {
        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                getBaseUrl() + "/date/2032423-07-07",
                ByParams.class);

        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testInvalidCountry_thenBadRequest() {
        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                getBaseUrl() + "/country/dont_exist",
                ByParams.class);

        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testAdvancedDate_inByParams_thenBadRequest() {
        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                getBaseUrl() + "/date/2023-07-07/country/Portugal",
                ByParams.class);

        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testBadFormatDate_inByParams_thenBadRequest() {
        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                getBaseUrl() + "/date/2435023-07-07/country/Portugal",
                ByParams.class);

        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testInvalidCountry_inByParams_thenBadRequest() {
        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                getBaseUrl() + "/date/2020-07-07/country/dont_exist",
                ByParams.class);

        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testRequestCountry_thenStatus200() {
        ByParams byParams = createTestClass();

        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                "/api/country/" + byParams.getCountry(),
                ByParams.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).extracting(ByParams::getCountry).isEqualTo(byParams.getCountry());

    }

    @Test
    public void testRequestDate_thenStatus200() {
        ByParams byParams = createTestClass();

        ResponseEntity<ByParams> response = restTemplate.getForEntity(
                "/api/date/" + byParams.getDate(),
                ByParams.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).extracting(ByParams::getDate).isEqualTo(byParams.getDate());

    }

    @Test
    public void testRequestParams_thenStatus200() {
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
        byParams.setConfirmed_diff(1500);
        byParams.setActive_diff(-15);
        byParams.setDeaths_diff(12);
        byParams.setRecovered_diff(3500);
        byParams.setFatality_rate(0.035);
        byParams.setDate("2020-07-20");
        byParams.setLast_updated((new Date(System.currentTimeMillis()).toString()));

        return byParams;
    }

    public String getBaseUrl() {
        return "http://localhost:" + randomServerPort + "/api";

    }
}
