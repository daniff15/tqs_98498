package com.example.demo.resolver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import com.example.demo.cache.Cache;
import com.example.demo.entities.ByParams;
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
    void callCountriesConverter() throws Exception {
        List<String> response = resolver
                .convertToCountries("{\"data\": [{ name : \"Portugal\"}, { name : \"China\"}]}");

        assertEquals(2, response.size());
        assertEquals(true, response.contains("Portugal"));
        assertEquals(true, response.contains("China"));
    }

    @Test
    void callConvertDate_BadAdvancedDate() throws Exception {
        assertThrows(Exception.class, () -> {
            resolver.convertJSONbyDatetoByParams("{\"data\": { \"date\" : \"2023-07-07\"}");
        });
    }

    @Test
    void callConvertDate_BadFormattedDate() throws Exception {
        assertThrows(Exception.class, () -> {
            resolver.convertJSONbyDatetoByParams("{\"data\": { \"date\" : \"20223423-07-07\"}");
        });
    }

    @Test
    void callConvertDate_ValidDate() throws Exception {

        ByParams byParams = resolver.convertJSONbyDatetoByParams(
                "{\"data\": { \"date\" : \"2020-07-07\" , \"last_update\":\"2020-04-07 23:11:31\", \"confirmed\":1426096 , \"confirmed_diff\":80995, \"deaths\":81865, \"deaths_diff\":7300, \"recovered\":300054, \"recovered_diff\":23539, \"active\":1044177, \"active_diff\":50156, \"fatality_rate\":0.0574}}");

        assertThat(byParams.getDate()).isEqualTo("2020-07-07");
        assertThat(byParams.getLast_updated()).isEqualTo("2020-04-07 23:11:31");
        assertThat(byParams.getConfirmed()).isEqualTo(1426096);
        assertThat(byParams.getConfirmed_diff()).isEqualTo(80995);
        assertThat(byParams.getDeaths()).isEqualTo(81865);
        assertThat(byParams.getDeaths_diff()).isEqualTo(7300);
        assertThat(byParams.getRecovered()).isEqualTo(300054);
        assertThat(byParams.getRecovered_diff()).isEqualTo(23539);
        assertThat(byParams.getActive()).isEqualTo(1044177);
        assertThat(byParams.getActive_diff()).isEqualTo(50156);
        assertThat(byParams.getFatality_rate()).isEqualTo(0.0574);
    }

    @Test
    void callConvertCountry_BadCountry() {
        assertThrows(Exception.class, () -> {
            resolver.convertJSONbyCountrytoByParams("", "dont_exist");
        });
    }

    @Test
    void callConvertCountry_ValidCountry() throws Exception {
        ByParams byParams = resolver.convertJSONbyCountrytoByParams(
                "{\"data\": [{ \"date\" : \"2020-07-07\" , \"last_update\":\"2020-04-07 23:11:31\", \"confirmed\":1426096 , \"confirmed_diff\":80995, \"deaths\":81865, \"deaths_diff\":7300, \"recovered\":300054, \"recovered_diff\":23539, \"active\":1044177, \"active_diff\":50156, \"fatality_rate\":0.0574}]}",
                "Portugal");

        assertThat(byParams.getDate()).isEqualTo("2020-07-07");
        assertThat(byParams.getLast_updated()).isEqualTo("2020-04-07 23:11:31");
        assertThat(byParams.getConfirmed()).isEqualTo(1426096);
        assertThat(byParams.getConfirmed_diff()).isEqualTo(80995);
        assertThat(byParams.getDeaths()).isEqualTo(81865);
        assertThat(byParams.getDeaths_diff()).isEqualTo(7300);
        assertThat(byParams.getRecovered()).isEqualTo(300054);
        assertThat(byParams.getRecovered_diff()).isEqualTo(23539);
        assertThat(byParams.getActive()).isEqualTo(1044177);
        assertThat(byParams.getActive_diff()).isEqualTo(50156);
        assertThat(byParams.getFatality_rate()).isEqualTo(0.0574);
        assertThat(byParams.getCountry()).isEqualTo("Portugal");

    }

    @Test
    void callConvertByParams_BadCountry() {
        assertThrows(Exception.class, () -> {
            resolver.convertJSONbyCountrytoByParams("", "dont_exist");
        });
    }

    @Test
    void callConvertByParams_AdvancedDate() {
        assertThrows(Exception.class, () -> {
            resolver.convertJSONbyCountrytoByParams("{\"data\": [{ \"date\" : \"2023-07-07\"}]}", "Portugal");
        });
    }

    @Test
    void callConvertByParams_BadFormatDate() {
        assertThrows(Exception.class, () -> {
            resolver.convertJSONbyCountrytoByParams("{\"data\": [{ \"date\" : \"2022433-07-07\"}]}", "Portugal");
        });
    }

    @Test
    void callConvertByParams_Valid() throws Exception {
        ByParams byParams = resolver.convertJSONbyParamstoByParams(
                "{\"data\": [{ \"date\" : \"2020-07-07\" , \"last_update\":\"2020-04-07 23:11:31\", \"confirmed\":1426096 , \"confirmed_diff\":80995, \"deaths\":81865, \"deaths_diff\":7300, \"recovered\":300054, \"recovered_diff\":23539, \"active\":1044177, \"active_diff\":50156, \"fatality_rate\":0.0574}]}",
                "Portugal");

        assertThat(byParams.getDate()).isEqualTo("2020-07-07");
        assertThat(byParams.getLast_updated()).isEqualTo("2020-04-07 23:11:31");
        assertThat(byParams.getConfirmed()).isEqualTo(1426096);
        assertThat(byParams.getConfirmed_diff()).isEqualTo(80995);
        assertThat(byParams.getDeaths()).isEqualTo(81865);
        assertThat(byParams.getDeaths_diff()).isEqualTo(7300);
        assertThat(byParams.getRecovered()).isEqualTo(300054);
        assertThat(byParams.getRecovered_diff()).isEqualTo(23539);
        assertThat(byParams.getActive()).isEqualTo(1044177);
        assertThat(byParams.getActive_diff()).isEqualTo(50156);
        assertThat(byParams.getFatality_rate()).isEqualTo(0.0574);
        assertThat(byParams.getCountry()).isEqualTo("Portugal");

    }
}
