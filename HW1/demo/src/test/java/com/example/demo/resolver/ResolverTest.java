package com.example.demo.resolver;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResolverTest {
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
        assertThat(resolver.getResponse("/reports/total?date=202020220-20-07")).isNotEmpty();
    }

}
