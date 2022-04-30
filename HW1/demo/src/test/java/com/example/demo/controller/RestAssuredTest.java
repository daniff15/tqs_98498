package com.example.demo.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.controller.CovidController;
import com.example.demo.entities.ByParams;
import com.example.demo.service.CovidService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.hamcrest.CoreMatchers.*;

@WebMvcTest(CovidController.class)
class RestAssuredTest {

    private ByParams byParams;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CovidService covidService;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssuredMockMvc.mockMvc(mvc);

        byParams = new ByParams();
        byParams.setDate("20-07-2020");
        byParams.setActive(500);
        byParams.setConfirmed(1000000);
        byParams.setRecovered(900000);
        byParams.setDeaths(50000);
        byParams.setCountry("Portugal");
        byParams.setFatalityRate(0.0038);
    }

    @Test
    void whenSearchByDate_thenReturn() throws Exception {

        when(covidService.getByDate("2020-07-20")).thenReturn(byParams);

        RestAssuredMockMvc.given().header("Content-Type", "application/json").body(byParams)
                .get("/api/date/2020-07-20")
                .then().assertThat().statusCode(200)
                .and().body("active", equalTo(500))
                .and().body("confirmed", equalTo(1000000))
                .and().body("recovered", equalTo(900000))
                .and().body("deaths", equalTo(50000))
                .and().body("country", equalTo("Portugal"))
                .and().body("fatalityRate", equalTo(0.0038F));

        verify(covidService, times(1)).getByDate("2020-07-20");
    }

    @Test
    void whenSearchByCountry_thenReturn() throws Exception {

        byParams.setConfirmed(1500000);

        when(covidService.getByCountry("Portugal")).thenReturn(byParams);

        RestAssuredMockMvc.given().header("Content-Type", "application/json").body(byParams)
                .get("/api/country/Portugal")
                .then().assertThat().statusCode(200)
                .and().body("active", equalTo(500))
                .and().body("confirmed", equalTo(1500000))
                .and().body("recovered", equalTo(900000))
                .and().body("deaths", equalTo(50000))
                .and().body("country", equalTo("Portugal"))
                .and().body("fatalityRate", equalTo(0.0038F));

        verify(covidService, times(1)).getByCountry("Portugal");
    }

    @Test
    void whenSearchByCountryAndDate_thenReturn() throws Exception {
        byParams.setActive(550);

        when(covidService.getByParams("2020-07-20", "Portugal")).thenReturn(byParams);

        RestAssuredMockMvc.given().header("Content-Type", "application/json").body(byParams)
                .get("/api/date/2020-07-20/country/Portugal")
                .then().assertThat().statusCode(200)
                .and().body("active", equalTo(550))
                .and().body("confirmed", equalTo(1000000))
                .and().body("recovered", equalTo(900000))
                .and().body("deaths", equalTo(50000))
                .and().body("country", equalTo("Portugal"))
                .and().body("fatalityRate", equalTo(0.0038F));

        verify(covidService, times(1)).getByParams("2020-07-20", "Portugal");
    }

}
