package com.example.restservice.persistance.repositories;

import com.example.restservice.persistance.model.Customer;
import com.example.restservice.persistance.model.HourlyStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HourlyStatsRepository extends JpaRepository<HourlyStats, Long> {

    List<HourlyStats> findAllByCreateDateAndCustomer(Date date, Customer customer);

}
