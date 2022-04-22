package com.example.demo.cache;

import java.sql.Date;
import java.util.List;

import com.example.demo.entities.ByParams;
import com.example.demo.repository.CovidRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Cache {

    @Autowired
    CovidRepository covidRepository;

    private int timeToLive; // in seconds
    // private int hits;
    // private int miss;
    // private int num_requests;

    private static final Logger logger = LoggerFactory.getLogger(Cache.class);

    public Cache() {
        this.timeToLive = 120;
        // this.hits = 0;
        // this.miss = 0;
        // this.num_requests = 0;
    }

    public ByParams getByDate(String date) {
        ByParams byDate = covidRepository.findByDate(date);

        if (byDate != null) {
            if (hasExpired(byDate)) {
                logger.info("Data expired in the cache");
                covidRepository.delete(byDate);
                return null;
            }
        }
        return byDate;
    }

    public ByParams getByCountry(String country) {
        ByParams byCountry = covidRepository.findByCountry(country);
        if (byCountry != null) {
            if (hasExpired(byCountry)) {
                logger.info("Data expired in the cache");
                covidRepository.delete(byCountry);
                return null;
            }
        }
        return byCountry;
    }

    public ByParams getByParametros(String date, String country) {
        ByParams byParametros = covidRepository.findByDateAndCountry(date, country);

        if (byParametros != null) {
            if (hasExpired(byParametros)) {
                logger.info("Data expired in the cache");
                covidRepository.delete(byParametros);
                return null;
            }
        }
        return byParametros;
    }

    public boolean hasExpired(ByParams m) {
        logger.info("Check if Data {} is expired", m);
        Date mostRecentExpiredDate = new Date(System.currentTimeMillis() - this.timeToLive * 1000);
        return m.getDatecreation().before(mostRecentExpiredDate);
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void cleanExpiredCachedMeasurements() {
        logger.info("Clean expired cached data");
        Date ExpiredDate = new Date(System.currentTimeMillis() - this.timeToLive * 1000);
        List<ByParams> expiredDates = covidRepository.findAllByDatecreationIsLessThanEqual(ExpiredDate);

        for (ByParams byParams : expiredDates) {
            logger.info("Deleting expired ByParams: {}", byParams);
            covidRepository.delete(byParams);
        }

    }

}
