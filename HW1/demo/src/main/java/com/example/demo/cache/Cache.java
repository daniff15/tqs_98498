package com.example.demo.cache;

import java.sql.Date;
import java.util.List;

import com.example.demo.entities.ByParams;
import com.example.demo.repository.CovidRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Cache {

    @Autowired
    CovidRepository covidRepository;

    private int timeToLive; // in seconds

    public Cache(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public Cache() {
        this.timeToLive = 120;
    }

    public ByParams getByDate(String date) {
        ByParams byDate = covidRepository.findByDate(date);

        if (byDate != null) {
            if (hasExpired(byDate)) {
                // log.info("Measurement expired in the cache");
                covidRepository.delete(byDate);
                return null;
            }
        }
        return byDate;
    }

    public boolean hasExpired(ByParams m) {
        // log.info("Checking if Measurement {} is expired", m);
        Date mostRecentExpiredDate = new Date(System.currentTimeMillis() - this.timeToLive * 1000);
        return m.getDatecreation().before(mostRecentExpiredDate);
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void cleanExpiredCachedMeasurements() {
        // log.info("Running scheduled method to clean expired cached measurements");
        Date ExpiredDate = new Date(System.currentTimeMillis() - this.timeToLive * 1000);
        List<ByParams> expiredDates = covidRepository.findAllByDatecreationIsLessThanEqual(ExpiredDate);

        for (ByParams byParams : expiredDates) {
            // log.info("Deleting expired ByParams: {}", m);
            covidRepository.delete(byParams);
        }

    }

}
