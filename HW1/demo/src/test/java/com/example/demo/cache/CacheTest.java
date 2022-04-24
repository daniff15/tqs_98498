package com.example.demo.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.example.demo.entities.ByParams;
import com.example.demo.repository.CovidRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CacheTest {
    private Integer active;
    private Integer confirmed;
    private Integer recovered;
    private Integer deaths;
    private String date;
    private Date datecreation;
    private String country;
    private ByParams byParams;

    @Mock
    CovidRepository covidRepository;

    @InjectMocks
    Cache cache;

    @BeforeEach
    void setUp() {
        ByParams byParams = new ByParams();
        this.byParams = byParams;
        this.active = 12345;
        byParams.setActive(active);
        this.confirmed = 1234567;
        byParams.setConfirmed(confirmed);
        this.recovered = 54321;
        byParams.setRecovered(recovered);
        this.deaths = 1000;
        byParams.setDeaths(deaths);
        this.date = "2020-11-11";
        byParams.setDate(date);
        this.country = "Portugal";
        byParams.setCountry(country);
        this.datecreation = new Date(System.currentTimeMillis());
        byParams.setDatecreation(datecreation);

    }

    @Test
    void testWithRightParams() {

        when(covidRepository.findByDateAndCountry(this.date, this.country))
                .thenReturn(this.byParams);
        ByParams cacheParams = cache.getByParametros(this.date, this.country);

        assertNotNull(cacheParams);

        assertEquals(this.active, cacheParams.getActive());
        assertEquals(this.confirmed, cacheParams.getConfirmed());
        assertEquals(this.recovered, cacheParams.getRecovered());
        assertEquals(this.deaths, cacheParams.getDeaths());
        assertEquals(this.date, cacheParams.getDate());
        assertEquals(this.country, cacheParams.getCountry());
        assertEquals(this.datecreation, cacheParams.getDatecreation());
        assertEquals(0, cacheParams.getActive_diff());
        assertEquals(0, cacheParams.getConfirmed_diff());
        assertEquals(0, cacheParams.getDeaths_diff());
        assertEquals(0, cacheParams.getRecovered_diff());
        assertNull(cacheParams.getFatality_rate());
        assertNull(cacheParams.getLast_updated());

        verify(covidRepository, times(1)).findByDateAndCountry(anyString(), anyString());
    }

    @Test
    void testInvalidCountry() {
        when(covidRepository.findByCountry("País Inventado"))
                .thenReturn(null);

        ByParams cacheParams = cache.getByCountry("País Inventado");

        assertNull(cacheParams);

        verify(covidRepository, times(1)).findByCountry(anyString());

    }

    @Test
    void testInvalidDate() {
        when(covidRepository.findByDate("2020-2020-2020"))
                .thenReturn(null);

        ByParams cacheParams = cache.getByDate("2020-2020-2020");

        assertNull(cacheParams);

        verify(covidRepository, times(1)).findByDate(anyString());

    }

    @Test
    void testGetExpiredMeasurement() {
        this.datecreation = new Date(System.currentTimeMillis() - 125 * 1000); // more than 120 sec
        this.byParams.setDatecreation(this.datecreation);

        when(covidRepository.findByDateAndCountry(this.date, this.country))
                .thenReturn(this.byParams);
        ByParams cacheParams = cache.getByParametros(this.date, this.country);

        assertNull(cacheParams);

        verify(covidRepository, times(1)).findByDateAndCountry(anyString(), anyString());
    }

    @Test
    void testScheduleCleaningCache() {
        this.datecreation = new Date(System.currentTimeMillis() - 125 * 1000); // A date with more that 120 sec
        this.byParams.setDatecreation(this.datecreation);
        when(covidRepository.findAllByDatecreationIsLessThanEqual(any(Date.class)))
                .thenReturn(new ArrayList<>(Arrays.asList(
                        this.byParams)));

        cache.cleanExpiredCache();

        verify(covidRepository, times(1)).findAllByDatecreationIsLessThanEqual(any(Date.class));

        verify(covidRepository, times(1)).delete(this.byParams);

        assertNull(cache.getByParametros(this.date, this.country));

    }

    @Test
    void testHasExpiredValid() {
        assertFalse(cache.hasExpired(this.byParams));
    }

    @Test
    void testHasExpiredInvalid() {
        this.datecreation = new Date(System.currentTimeMillis() - 125 * 1000); // A date with more that 120 sec
        this.byParams.setDatecreation(datecreation);
        assertTrue(cache.hasExpired(this.byParams));
    }

    @Test
    void testDelete() {
        cache.covidRepository.delete(this.byParams);
        verify(covidRepository, times(1)).delete(this.byParams);
    }

    @AfterEach
    void tearDown() {
        covidRepository.deleteAll();
    }

}
