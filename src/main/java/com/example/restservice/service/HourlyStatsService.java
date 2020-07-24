package com.example.restservice.service;

import com.example.restservice.persistance.model.RequestLogEntry;
import com.example.restservice.persistance.repositories.RequestLogEntryRepository;
import com.example.restservice.persistance.model.Customer;
import com.example.restservice.persistance.model.HourlyStats;
import com.example.restservice.persistance.repositories.CustomerRepository;
import com.example.restservice.persistance.repositories.HourlyStatsRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
            This service is responsible about gathering logs each {pre set} interval and saving them in HourlyStats table
 */

@Service
@EnableScheduling
public class HourlyStatsService {
    final HourlyStatsRepository hourlyStatsRepository;
    final RequestLogEntryRepository requestLogEntryRepository;
    final CustomerRepository customerRepository;

    public static final long HOURLY_STATS_AGGREGATION_INTERVAL = 20*1000;

    public HourlyStatsService(HourlyStatsRepository hourlyStatsRepository,
                              RequestLogEntryRepository requestLogEntryRepository,
                              CustomerRepository customerRepository) {

        this.hourlyStatsRepository = hourlyStatsRepository;
        this.requestLogEntryRepository = requestLogEntryRepository;
        this.customerRepository = customerRepository;
    }
    @Scheduled(fixedRate=HOURLY_STATS_AGGREGATION_INTERVAL)
    public void scheduleFixedRateTask() {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000);

        /*
                Getting all customers form db
         */
        List<Customer> customerList = customerRepository.findAll();

        /*
                Fetching list of all log entries per customer
         */
        try {
            Map<Integer, List<RequestLogEntry>> map = new HashMap<>();
            customerList.forEach(customer -> {
                List<RequestLogEntry> requestLogEntry = requestLogEntryRepository.findAllByCustomerId(customer.getId());
                map.put(customer.getId(), requestLogEntry);
            });


             /*
                Count and save all valid/invalid requests and save to db
            */
            map.forEach((customerId, logList) ->{
                long validRequestCount;
                long invalidRequestCount;

                validRequestCount = logList.stream().filter(item -> item.isValid()).count();

                invalidRequestCount = logList.size() - validRequestCount;

                HourlyStats hourlyStats = new HourlyStats();
                hourlyStats.setRequestCount(validRequestCount);
                hourlyStats.setInvalidCount(invalidRequestCount);
                hourlyStats.setCustomer(customerRepository.getOne(customerId));

                /*
                       If customer has 0 requests it will not be logged
                 */

                if(logList.size() >0) {
                    hourlyStatsRepository.save(hourlyStats);
                }
            });

        }catch (Exception e){
            System.out.println("Exception accrued, most likely request log list is empty");
        }


            /*
                    Clear table
             */
        requestLogEntryRepository.deleteAllWithQuery();


    }


}