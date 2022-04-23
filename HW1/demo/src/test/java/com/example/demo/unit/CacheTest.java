package com.example.demo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.demo.cache.Cache;
import com.example.demo.entities.ByParams;
import com.example.demo.repository.CovidRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CacheTest {
    private Integer active;
    private Integer confirmed;
    private Integer recovered;
    private Integer deaths;
    private String date;
    private String country;
    private ByParams byParams;

    @Mock
    private CovidRepository covidRepository;

    @InjectMocks
    private Cache cache;

    @BeforeEach
    void setUp() throws InterruptedException {
        cache = new Cache();
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
    }

    @Test
    void addValueTest() {
        when(covidRepository.findByDateAndCountry("2020-11-11", "Portugal"))
                .thenReturn(this.byParams);

        ByParams cacheParams = cache.getByParametros("2020-11-11", "Portugal");

        assertNotNull(cacheParams);

        // assertEquals(0, byParams.size());
        // cache.addToCache(new ByParams("2020-11-11", "2020-11-11", 12345, 14, 23, 100,
        // 4000,
        // 4000, 12, 54, 0.0123, "Portugal"));
        // assertEquals(1, this.cache.getCacheSize());
    }

    @AfterEach
    void tearDown() {
        covidRepository.deleteAll();
    }

}
