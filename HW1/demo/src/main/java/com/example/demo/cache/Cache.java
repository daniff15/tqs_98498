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
    private static final String EXPIREDMSG = "Data expired in cache";  // Compliant

    @Autowired
    CovidRepository covidRepository;

    private int timeToLive; // in seconds
    private int hits = 0;
    private int misses = 0;
    private int requestCount = 0;

    private static final Logger logger = LoggerFactory.getLogger(Cache.class);

    public Cache() {
        this.timeToLive = 120;
    }

    public void addHit() {
        this.hits++;
    }

    public void addMiss() {
        this.misses++;
    }

    public ByParams getByDate(String date) {
        ByParams byDate = covidRepository.findByDate(date);

        if (byDate != null && hasExpired(byDate)) {
            logger.info(EXPIREDMSG);
            covidRepository.delete(byDate);
            return null;
        }
        return byDate;
    }

    public ByParams getByCountry(String country) {
        ByParams byCountry = covidRepository.findByCountry(country);
        if (byCountry != null && hasExpired(byCountry)) {
            logger.info(EXPIREDMSG);
            covidRepository.delete(byCountry);
            return null;
        }
        return byCountry;
    }

    public ByParams getByParametros(String date, String country) {
        ByParams byParametros = covidRepository.findByDateAndCountry(date, country);

        if (byParametros != null && hasExpired(byParametros)) {
            logger.info(EXPIREDMSG);
            covidRepository.delete(byParametros);
            return null;
        }
        return byParametros;
    }

    public boolean hasExpired(ByParams m) {
        logger.info("Check if Data {} is expired", m);
        Date mostRecentExpiredDate = new Date(System.currentTimeMillis() - this.timeToLive * 1000);
        return m.getDatecreation().before(mostRecentExpiredDate);
    }

    public int getHits() {
        return this.hits;
    }

    public int getMisses() {
        return this.misses;
    }

    public int getRequestCount() {
        return this.requestCount;
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void cleanExpiredCache() {
        logger.info("Clean expired cached data");
        Date expiredDate = new Date(System.currentTimeMillis() - this.timeToLive * 1000);
        List<ByParams> expiredDates = covidRepository.findAllByDatecreationIsLessThanEqual(expiredDate);

        for (ByParams byParams : expiredDates) {
            logger.info("Deleting expired ByParams: {}", byParams);
            covidRepository.delete(byParams);
        }

    }

}
