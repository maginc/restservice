package com.example.restservice.model;

import com.example.restservice.persistance.model.Customer;
import com.example.restservice.persistance.model.HourlyStats;

import java.util.List;

public class StatsResponseBuilder {
    private Customer customer;
    private String requestedDate;
    private long totalRequest;
    private long totalValid;
    private long totalInvalid;
    private List<HourlyStats> hourlyStats;

    public StatsResponseBuilder setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
        return this;
    }

    public StatsResponseBuilder setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public StatsResponseBuilder setHourlyStats(List<HourlyStats> hourlyStats) {
        this.hourlyStats = hourlyStats;
        return this;
    }

    public StatsResponseBuilder setTotalRequest(long totalRequest) {
        this.totalRequest = totalRequest;
        return this;
    }

    public StatsResponseBuilder setTotalValid(long totalValid) {
        this.totalValid = totalValid;
        return this;
    }

    public StatsResponseBuilder setTotalInvalid(long totalInvalid) {
        this.totalInvalid = totalInvalid;
        return this;
    }

    public StatsResponse createStatsResponse() {
        return new StatsResponse(customer, requestedDate, totalRequest, totalValid, totalInvalid,hourlyStats);
    }
}