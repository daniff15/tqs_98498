package com.example.demo.resolver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import com.example.demo.entities.ByParams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ResolverTest {
    private int active;
    private int confirmed;
    private int recovered;
    private int deaths;
    private String date;
    private Date datecreation;
    private String country;
    private ByParams byParams;

    @Mock
    Resolver resolver;

    @BeforeEach
    public void setUp() throws URISyntaxException, IOException, InterruptedException {

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

        // Mock calls
        Mockito.when(resolver.getResponse("/reports?region_name=Portugal")).thenReturn(value)

    }

    @Test
    public void getResponse_thenReturn() {
    }

}
