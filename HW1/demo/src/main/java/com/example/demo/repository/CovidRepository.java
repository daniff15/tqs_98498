package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import com.example.demo.entities.ByParams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidRepository extends JpaRepository<ByParams, String> {

    // private static final Logger log = LoggerFactory.getLogger(Cache.class);
    ByParams findByCountry(String country);

    ByParams findByDate(String date);

    List<ByParams> findAllByDatecreationIsLessThanEqual(Date date);

    ByParams findByDateAndCountryAndProvince(String date, String country, String province);
}
