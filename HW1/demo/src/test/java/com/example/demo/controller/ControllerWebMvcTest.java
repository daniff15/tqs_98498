package com.example.demo.controller;

import com.example.demo.controller.CovidController;
import com.example.demo.entities.ByParams;
import com.example.demo.service.CovidService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import java.util.Date;

@WebMvcTest(CovidController.class)
public class ControllerWebMvcTest {

        @Autowired
        private MockMvc mvc;

        @MockBean
        private CovidService covidService;

        @Test
        public void testWithGoodDate_thenStatus200() throws Exception {
                ByParams byParams = createTestClass();

                when(covidService.getByDate(byParams.getDate()))
                                .thenReturn(byParams);

                mvc.perform(get("/api/date/" + byParams.getDate())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());

                verify(covidService, times(1)).getByDate(anyString());

        }

        @Test
        public void testWithGoodDate_thenGoodData() throws Exception {
                ByParams byParams = createTestClass();

                when(covidService.getByDate(byParams.getDate()))
                                .thenReturn(byParams);

                mvc.perform(get("/api/date/" + byParams.getDate())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("active", is(byParams.getActive())))
                                .andExpect(jsonPath("confirmed", is(byParams.getConfirmed())))
                                .andExpect(jsonPath("recovered", is(byParams.getRecovered())))
                                .andExpect(jsonPath("deaths", is(byParams.getDeaths())))
                                .andExpect(jsonPath("country", is(byParams.getCountry())))
                                .andExpect(jsonPath("confirmedDiff", is(byParams.getConfirmedDiff())))
                                .andExpect(jsonPath("activeDiff", is(byParams.getActiveDiff())))
                                .andExpect(jsonPath("deathsDiff", is(byParams.getDeathsDiff())))
                                .andExpect(jsonPath("recoveredDiff", is(byParams.getRecoveredDiff())))
                                .andExpect(jsonPath("fatalityRate", is(byParams.getFatalityRate())))
                                .andExpect(jsonPath("date", is(byParams.getDate())))
                                .andExpect(jsonPath("lastUpdated", is(byParams.getLastUpdated())));

                verify(covidService, times(1)).getByDate(anyString());

        }

        @Test
        public void testWithGoodCountry_thenStatus200() throws Exception {
                ByParams byParams = createTestClass();

                when(covidService.getByCountry(byParams.getCountry()))
                                .thenReturn(byParams);

                mvc.perform(get("/api/country/" + byParams.getCountry())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());

                verify(covidService, times(1)).getByCountry(anyString());

        }

        @Test
        public void testWithGoodCountry_thenGoodData() throws Exception {
                ByParams byParams = createTestClass();

                when(covidService.getByCountry(byParams.getCountry()))
                                .thenReturn(byParams);

                mvc.perform(get("/api/country/" + byParams.getCountry())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("active", is(byParams.getActive())))
                                .andExpect(jsonPath("confirmed", is(byParams.getConfirmed())))
                                .andExpect(jsonPath("recovered", is(byParams.getRecovered())))
                                .andExpect(jsonPath("deaths", is(byParams.getDeaths())))
                                .andExpect(jsonPath("country", is(byParams.getCountry())))
                                .andExpect(jsonPath("confirmedDiff", is(byParams.getConfirmedDiff())))
                                .andExpect(jsonPath("activeDiff", is(byParams.getActiveDiff())))
                                .andExpect(jsonPath("deathsDiff", is(byParams.getDeathsDiff())))
                                .andExpect(jsonPath("recoveredDiff", is(byParams.getRecoveredDiff())))
                                .andExpect(jsonPath("fatalityRate", is(byParams.getFatalityRate())))
                                .andExpect(jsonPath("date", is(byParams.getDate())))
                                .andExpect(jsonPath("lastUpdated", is(byParams.getLastUpdated())));

                verify(covidService, times(1)).getByCountry(anyString());

        }

        @Test
        public void testWithGoodDate_Country_thenStatus200() throws Exception {
                ByParams byParams = createTestClass();

                when(covidService.getByParams(byParams.getDate(), byParams.getCountry()))
                                .thenReturn(byParams);

                mvc.perform(get("/api/date/" + byParams.getDate() + "/country/" + byParams.getCountry())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());

                verify(covidService, times(1)).getByParams(anyString(), anyString());

        }

        @Test
        public void testWithGoodCountry_Date_thenGoodData() throws Exception {
                ByParams byParams = createTestClass();

                when(covidService.getByParams(byParams.getDate(), byParams.getCountry()))
                                .thenReturn(byParams);

                mvc.perform(get("/api/date/" + byParams.getDate() + "/country/" + byParams.getCountry())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("active", is(byParams.getActive())))
                                .andExpect(jsonPath("confirmed", is(byParams.getConfirmed())))
                                .andExpect(jsonPath("recovered", is(byParams.getRecovered())))
                                .andExpect(jsonPath("deaths", is(byParams.getDeaths())))
                                .andExpect(jsonPath("country", is(byParams.getCountry())))
                                .andExpect(jsonPath("confirmedDiff", is(byParams.getConfirmedDiff())))
                                .andExpect(jsonPath("activeDiff", is(byParams.getActiveDiff())))
                                .andExpect(jsonPath("deathsDiff", is(byParams.getDeathsDiff())))
                                .andExpect(jsonPath("recoveredDiff", is(byParams.getRecoveredDiff())))
                                .andExpect(jsonPath("fatalityRate", is(byParams.getFatalityRate())))
                                .andExpect(jsonPath("date", is(byParams.getDate())))
                                .andExpect(jsonPath("lastUpdated", is(byParams.getLastUpdated())));

                verify(covidService, times(1)).getByParams(anyString(), anyString());

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
