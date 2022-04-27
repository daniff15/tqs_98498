package com.example.demo.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.cache.Cache;
import com.example.demo.entities.ByParams;
import com.example.demo.repository.CovidRepository;
import com.example.demo.resolver.Resolver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ServiceMockTest {
    private int active;
    private int confirmed;
    private int recovered;
    private int deaths;
    private String date;
    private Date datecreation;
    private String country;
    private ByParams byParams;

    @Mock(lenient = true)
    private Cache cache;

    @Mock(lenient = true)
    private CovidRepository covidRepository;

    @Mock(lenient = true)
    private Resolver resolver;

    @InjectMocks
    private CovidService covidService;

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
        Mockito.when(cache.getByDate("2020-11-11")).thenReturn(byParams);
        Mockito.when(cache.getByParametros("2020-11-11", "Portugal")).thenReturn(byParams);
        Mockito.when(cache.getByCountry("Portugal")).thenReturn(byParams);
        Mockito.when(covidRepository.findByCountry("dont_exist")).thenReturn(null);
        Mockito.when(cache.getByCountry("dont_exist")).thenReturn(null);
        Mockito.when(resolver.getResponse("/reports?region_name=dont_exist")).thenReturn(null);
        Mockito.when(covidRepository.findByDate("2020-2020-2020")).thenReturn(null);
        Mockito.when(cache.getByDate("2020-2020-2020")).thenReturn(null);
        Mockito.when(resolver.getResponse("/reports/total?date=2020-2020-2020")).thenReturn(null);
        Mockito.when(covidRepository.findByDateAndCountry("2020-2020-2020", "dont_exist")).thenReturn(null);
        Mockito.when(cache.getByParametros("2020-2020-2020", "dont_exist")).thenReturn(null);
        Mockito.when(resolver.getResponse("/reports?region_name=dont_exist&date=2020-2020-2020")).thenReturn(null);

    }

    @Test
    public void whenSearchByValidCountryInCache_returnByParams()
            throws URISyntaxException, IOException, InterruptedException {

        ByParams found = covidService.getByCountry("Portugal");

        assertThat(found).isEqualTo(this.byParams);
        Mockito.verify(cache, VerificationModeFactory.times(1)).getByCountry("Portugal");
    }

    @Test
    public void whenSearchByValidDateInCache_returnByParams()
            throws URISyntaxException, IOException, InterruptedException {

        ByParams found = covidService.getByDate("2020-11-11");

        assertThat(found).isEqualTo(this.byParams);
        Mockito.verify(cache, VerificationModeFactory.times(1)).getByDate("2020-11-11");
    }

    @Test
    public void whenSearchByValidCountryAndDateInCache_returnByParams()
            throws URISyntaxException, IOException, InterruptedException {

        ByParams found = covidService.getByParams("2020-11-11", "Portugal");

        assertThat(found).isEqualTo(this.byParams);
        Mockito.verify(cache, VerificationModeFactory.times(1)).getByParametros("2020-11-11", "Portugal");
    }

    @Test
    void whenValidCountryInCache_thenShouldExist() throws URISyntaxException, IOException, InterruptedException {
        ByParams exists = covidService.getByCountry("Portugal");
        boolean classExists = false;
        if (exists != null) {
            classExists = true;
        }

        assertThat(classExists).isEqualTo(true);

        Mockito.verify(cache, VerificationModeFactory.times(1)).getByCountry("Portugal");
    }

    @Test
    void whenValidDateInCache_thenShouldExist() throws URISyntaxException, IOException, InterruptedException {
        ByParams exists = covidService.getByDate("2020-11-11");
        boolean classExists = false;
        if (exists != null) {
            classExists = true;
        }

        assertThat(classExists).isEqualTo(true);

        Mockito.verify(cache, VerificationModeFactory.times(1)).getByDate("2020-11-11");
    }

    @Test
    void whenValidCountryAndDateInCache_thenShouldExist() throws URISyntaxException, IOException, InterruptedException {
        ByParams exists = covidService.getByParams("2020-11-11", "Portugal");
        boolean classExists = false;
        if (exists != null) {
            classExists = true;
        }

        assertThat(classExists).isEqualTo(true);

        Mockito.verify(cache, VerificationModeFactory.times(1)).getByParametros("2020-11-11", "Portugal");
    }

    @Test
    void whenSearchInvalidCountry_thenShouldNotBeFound() throws URISyntaxException, IOException, InterruptedException {

        ByParams exists = covidService.getByCountry("dont_exist");
        boolean classExists = false;
        if (exists != null) {
            classExists = true;
        }

        assertThat(classExists).isEqualTo(false);

        Mockito.verify(cache, VerificationModeFactory.times(1)).getByCountry("dont_exist");

    }

    @Test
    void whenSearchInvalidDate_thenShouldNotBeFound() throws URISyntaxException, IOException, InterruptedException {

        ByParams exists = covidService.getByDate("2020-2020-2020");
        boolean classExists = false;
        if (exists != null) {
            classExists = true;
        }

        assertThat(classExists).isEqualTo(false);

        Mockito.verify(cache, VerificationModeFactory.times(1)).getByDate("2020-2020-2020");

    }

    @Test
    void whenSearchInvalidParameters_thenShouldNotBeFound()
            throws URISyntaxException, IOException, InterruptedException {

        ByParams exists = covidService.getByParams("2020-2020-2020", "dont_exist");
        boolean classExists = false;
        if (exists != null) {
            classExists = true;
        }

        assertThat(classExists).isEqualTo(false);

        Mockito.verify(cache, VerificationModeFactory.times(1)).getByParametros("2020-2020-2020", "dont_exist");

    }

    @Test
    void given3Things_whengetAll_thenReturn3Records() {
        ByParams byParams1 = new ByParams();
        ByParams byParams2 = new ByParams();
        ByParams byParams3 = new ByParams();

        byParams1.setCountry("Portugal");
        byParams2.setCountry("China");
        byParams3.setCountry("Brazil");

        List<ByParams> lista = new ArrayList<>();
        lista.add(byParams1);
        lista.add(byParams2);
        lista.add(byParams3);

        Mockito.when(covidRepository.findAll()).thenReturn(lista);

        List<ByParams> allByParams = covidService.getAllByParams();
        Mockito.verify(covidRepository, VerificationModeFactory.times(1)).findAll();
        assertThat(allByParams).hasSize(3).extracting(ByParams::getCountry).contains(byParams1.getCountry(),
                byParams2.getCountry(), byParams3.getCountry());
    }

}
