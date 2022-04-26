package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import com.example.demo.entities.ByParams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidRepository extends JpaRepository<ByParams, String> {

    // private static final Logger log = LoggerFactory.getLogger(Cache.class);
    ByParams findByCountry(String country);

    ByParams findByDate(String date);

    ByParams findById(Long id);

    List<ByParams> findAllByDatecreationIsLessThanEqual(Date date);

    ByParams findByDateAndCountry(String date, String country);
}
