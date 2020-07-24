package com.example.restservice.model;

import com.example.restservice.persistance.model.Customer;
import com.example.restservice.persistance.model.HourlyStats;

import java.util.List;
import java.util.Objects;

/**
 * @author Andris Magins
 * @created 24-Jul-20
 **/
public class StatsResponse {
    private Customer customer;
    private String requestedDate;
    private long totalRequests;
    private long totalValid;
    private long totalInvalid;
    private List<HourlyStats> hourlyStats;

    public StatsResponse() {
    }

    public StatsResponse(Customer customer, String requestedDate, long totalRequests, long totalValid, long totalInvalid,
                         List<HourlyStats> hourlyStats) {
        this.customer = customer;
        this.requestedDate = requestedDate;
        this.totalRequests = totalRequests;
        this.totalValid = totalValid;
        this.totalInvalid = totalInvalid;
        this.hourlyStats = hourlyStats;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<HourlyStats> getHourlyStats() {
        return hourlyStats;
    }

    public void setHourlyStats(List<HourlyStats> hourlyStats) {
        this.hourlyStats = hourlyStats;
    }

    public long getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(long totalRequests) {
        this.totalRequests = totalRequests;
    }

    public long getTotalValid() {
        return totalValid;
    }

    public void setTotalValid(long totalValid) {
        this.totalValid = totalValid;
    }

    public long getTotalInvalid() {
        return totalInvalid;
    }

    public void setTotalInvalid(long totalInvalid) {
        this.totalInvalid = totalInvalid;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatsResponse that = (StatsResponse) o;
        return totalRequests == that.totalRequests &&
                totalValid == that.totalValid &&
                totalInvalid == that.totalInvalid &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(requestedDate, that.requestedDate) &&
                Objects.equals(hourlyStats, that.hourlyStats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, requestedDate, totalRequests, totalValid, totalInvalid, hourlyStats);
    }

    @Override
    public String toString() {
        return "StatsResponse{" +
                "customer=" + customer +
                ", requestedDate=" + requestedDate +
                ", totalRequest=" + totalRequests +
                ", totalValid=" + totalValid +
                ", totalInvalid=" + totalInvalid +
                ", hourlyStats=" + hourlyStats +
                '}';
    }
}
