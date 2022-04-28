package com.example.demo.resolver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import com.example.demo.cache.Cache;
import com.example.demo.repository.CovidRepository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ResolverTest {
    @Mock
    private Cache cache;

    @Mock
    private CovidRepository covidRepository;

    @InjectMocks
    private Resolver resolver;

    @BeforeEach
    void setUp() {
        resolver = new Resolver();
    }

    @Test
    void callEXternalAPI_InvalidUrl() {
        assertThrows(Exception.class, () -> {
            resolver.getResponse("badURL");
        });
    }

    @Test
    void callEXternalAPI_ValidUrl() throws Exception {
        assertThat(resolver.getResponse("/reports/total?date=2020-20-07")).isNotEmpty();
    }

    @Test
    void callEXternalAPITest() throws Exception {
        List<String> response = resolver
                .convertToCountries("{\"data\": [{ name : \"Portugal\"}, { name : \"China\"}]}");

        assertEquals(2, response.size());
        assertEquals(true, response.contains("Portugal"));
        assertEquals(true, response.contains("China"));
    }

}
