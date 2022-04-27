package com.example.demo.repository;

import com.example.demo.entities.ByParams;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class RepositoryTest {

    private ByParams byParams;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CovidRepository covidRepository;

    @BeforeEach
    void setUp() {
        ByParams byParams = new ByParams();
        this.byParams = byParams;
        byParams.setActive(500);
        byParams.setConfirmed(5000);
        byParams.setRecovered(3500);
        byParams.setDeaths(5);
        byParams.setCountry("Lituânia");
        byParams.setDate("2020-07-20");

    }

    @Test
    void whenFindByDate_thenReturnObject() {
        testEntityManager.persistAndFlush(byParams); // ensure data is persisted at this

        ByParams found = covidRepository.findByDate(byParams.getDate());
        assertThat(found).isEqualTo(byParams);
    }

    @Test
    void whenFindByCountry_thenReturnObject() {
        testEntityManager.persistAndFlush(byParams); // ensure data is persisted at this

        ByParams found = covidRepository.findByCountry(byParams.getCountry());
        assertThat(found).isEqualTo(byParams);
    }

    @Test
    void whenFindByDateAndCountry_thenReturnObject() {
        testEntityManager.persistAndFlush(byParams); // ensure data is persisted at this

        ByParams found = covidRepository.findByDateAndCountry(byParams.getDate(), byParams.getCountry());
        assertThat(found).isEqualTo(byParams);
    }

    @Test
    void whenInvalidCountry_thenReturnNull() {
        ByParams fromDb = covidRepository.findByCountry("Does Not Exist");
        assertThat(fromDb).isNull();
    }

    @Test
    void whenInvalidDate_thenReturnNull() {
        ByParams fromDb = covidRepository.findByDate("2020-2020-2020");
        assertThat(fromDb).isNull();
    }

    @Test
    public void testWhenFindByExistingId_thenReturn() {
        testEntityManager.persistAndFlush(byParams);

        ByParams fromDb = covidRepository.findById(byParams.getId());
        assertThat(fromDb).isNotNull().isEqualTo(byParams);
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        ByParams fromDb = covidRepository.findById(-111L);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfByParams_whenFindAll_thenReturnAll() {
        ByParams byParams1 = new ByParams();
        ByParams byParams2 = new ByParams();
        ByParams byParams3 = new ByParams();

        byParams1.setCountry("Portugal");
        byParams2.setCountry("Brazil");
        byParams3.setCountry("China");

        testEntityManager.persist(byParams1);
        testEntityManager.persist(byParams2);
        testEntityManager.persist(byParams3);
        testEntityManager.flush();

        List<ByParams> allByParams = covidRepository.findAll();

        assertThat(allByParams).hasSize(3).extracting(ByParams::getCountry).containsOnly(byParams1.getCountry(),
                byParams2.getCountry(),
                byParams3.getCountry());
    }

    @AfterEach
    void tearDown() {
        covidRepository.deleteAll();
    }

}
