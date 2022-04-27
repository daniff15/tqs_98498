package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.entities.ByParams;
import com.example.demo.repository.CovidRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ByParamsControllerMockMvcIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CovidRepository covidRepository;

    @AfterEach
    public void resetDb() {
        covidRepository.deleteAll();
    }

    @Test
    void givenByCountry_whenGetThem_return() throws Exception {
        ByParams byParams1 = createTestClass();
        covidRepository.saveAndFlush(byParams1);

        mvc.perform(get("/api/country/Portugal").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("active", is(byParams1.getActive())))
                .andExpect(jsonPath("confirmed", is(byParams1.getConfirmed())))
                .andExpect(jsonPath("recovered", is(byParams1.getRecovered())))
                .andExpect(jsonPath("deaths", is(byParams1.getDeaths())))
                .andExpect(jsonPath("country", is(byParams1.getCountry())))
                .andExpect(jsonPath("confirmed_diff", is(byParams1.getConfirmed_diff())))
                .andExpect(jsonPath("active_diff", is(byParams1.getActive_diff())))
                .andExpect(jsonPath("deaths_diff", is(byParams1.getDeaths_diff())))
                .andExpect(jsonPath("recovered_diff", is(byParams1.getRecovered_diff())))
                .andExpect(jsonPath("fatality_rate", is(byParams1.getFatality_rate())))
                .andExpect(jsonPath("date", is(byParams1.getDate())))
                .andExpect(jsonPath("last_updated", is(byParams1.getLast_updated())));
    }

    @Test
    void givenByDate_whenGetThem_return() throws Exception {
        ByParams byParams1 = createTestClass();
        covidRepository.saveAndFlush(byParams1);

        mvc.perform(get("/api/date/2020-07-20").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("active", is(byParams1.getActive())))
                .andExpect(jsonPath("confirmed", is(byParams1.getConfirmed())))
                .andExpect(jsonPath("recovered", is(byParams1.getRecovered())))
                .andExpect(jsonPath("deaths", is(byParams1.getDeaths())))
                .andExpect(jsonPath("country", is(byParams1.getCountry())))
                .andExpect(jsonPath("confirmed_diff", is(byParams1.getConfirmed_diff())))
                .andExpect(jsonPath("active_diff", is(byParams1.getActive_diff())))
                .andExpect(jsonPath("deaths_diff", is(byParams1.getDeaths_diff())))
                .andExpect(jsonPath("recovered_diff", is(byParams1.getRecovered_diff())))
                .andExpect(jsonPath("fatality_rate", is(byParams1.getFatality_rate())))
                .andExpect(jsonPath("date", is(byParams1.getDate())))
                .andExpect(jsonPath("last_updated", is(byParams1.getLast_updated())));
    }

    @Test
    void givenByParams_whenGetThem_return() throws Exception {
        ByParams byParams1 = createTestClass();
        covidRepository.saveAndFlush(byParams1);

        mvc.perform(get("/api/date/2020-07-20/country/Portugal").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("active", is(byParams1.getActive())))
                .andExpect(jsonPath("confirmed", is(byParams1.getConfirmed())))
                .andExpect(jsonPath("recovered", is(byParams1.getRecovered())))
                .andExpect(jsonPath("deaths", is(byParams1.getDeaths())))
                .andExpect(jsonPath("country", is(byParams1.getCountry())))
                .andExpect(jsonPath("confirmed_diff", is(byParams1.getConfirmed_diff())))
                .andExpect(jsonPath("active_diff", is(byParams1.getActive_diff())))
                .andExpect(jsonPath("deaths_diff", is(byParams1.getDeaths_diff())))
                .andExpect(jsonPath("recovered_diff", is(byParams1.getRecovered_diff())))
                .andExpect(jsonPath("fatality_rate", is(byParams1.getFatality_rate())))
                .andExpect(jsonPath("date", is(byParams1.getDate())))
                .andExpect(jsonPath("last_updated", is(byParams1.getLast_updated())));
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
}
