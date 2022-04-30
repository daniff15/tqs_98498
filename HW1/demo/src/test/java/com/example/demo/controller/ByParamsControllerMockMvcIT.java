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
    void searchByCountry_whenGetThem_return() throws Exception {
        ByParams byParams1 = createTestClass();
        covidRepository.saveAndFlush(byParams1);

        mvc.perform(get("/api/country/Portugal").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("active", is(byParams1.getActive())))
                .andExpect(jsonPath("confirmed", is(byParams1.getConfirmed())))
                .andExpect(jsonPath("recovered", is(byParams1.getRecovered())))
                .andExpect(jsonPath("deaths", is(byParams1.getDeaths())))
                .andExpect(jsonPath("country", is(byParams1.getCountry())))
                .andExpect(jsonPath("confirmed_diff", is(byParams1.getConfirmedDiff())))
                .andExpect(jsonPath("active_diff", is(byParams1.getActiveDiff())))
                .andExpect(jsonPath("deaths_diff", is(byParams1.getDeathsDiff())))
                .andExpect(jsonPath("recovered_diff", is(byParams1.getRecoveredDiff())))
                .andExpect(jsonPath("fatality_rate", is(byParams1.getFatalityRate())))
                .andExpect(jsonPath("date", is(byParams1.getDate())))
                .andExpect(jsonPath("last_updated", is(byParams1.getLastUpdated())));
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
                .andExpect(jsonPath("confirmed_diff", is(byParams1.getConfirmedDiff())))
                .andExpect(jsonPath("active_diff", is(byParams1.getActiveDiff())))
                .andExpect(jsonPath("deaths_diff", is(byParams1.getDeathsDiff())))
                .andExpect(jsonPath("recovered_diff", is(byParams1.getRecoveredDiff())))
                .andExpect(jsonPath("fatality_rate", is(byParams1.getFatalityRate())))
                .andExpect(jsonPath("date", is(byParams1.getDate())))
                .andExpect(jsonPath("last_updated", is(byParams1.getLastUpdated())));
    }

    @Test
    void getByParams_whenGetThem_return() throws Exception {
        ByParams byParams1 = createTestClass();
        covidRepository.saveAndFlush(byParams1);

        mvc.perform(get("/api/date/2020-07-20/country/Portugal").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("active", is(byParams1.getActive())))
                .andExpect(jsonPath("confirmed", is(byParams1.getConfirmed())))
                .andExpect(jsonPath("recovered", is(byParams1.getRecovered())))
                .andExpect(jsonPath("deaths", is(byParams1.getDeaths())))
                .andExpect(jsonPath("country", is(byParams1.getCountry())))
                .andExpect(jsonPath("confirmed_diff", is(byParams1.getConfirmedDiff())))
                .andExpect(jsonPath("active_diff", is(byParams1.getActiveDiff())))
                .andExpect(jsonPath("deaths_diff", is(byParams1.getDeathsDiff())))
                .andExpect(jsonPath("recovered_diff", is(byParams1.getRecoveredDiff())))
                .andExpect(jsonPath("fatality_rate", is(byParams1.getFatalityRate())))
                .andExpect(jsonPath("date", is(byParams1.getDate())))
                .andExpect(jsonPath("last_updated", is(byParams1.getLastUpdated())));
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
}
