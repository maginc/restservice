package com.example.restservice.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "hourly_stats")
public class HourlyStats {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date createDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_stamp")
    private Date timestamp;

    @Column(name = "request_count")
    private long requestCount = 0;

    @Column(name = "invalid_count")
    private long invalidCount = 0;

    @ManyToOne
    @JoinColumn(name = "customer", nullable = false)
    @JsonIgnore
    private Customer customer;


    public Date getCreateDate() {
        return createDate;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(long requestCount) {
        this.requestCount = requestCount;
    }

    public long getInvalidCount() {
        return invalidCount;
    }

    public void setInvalidCount(long invalidCount) {
        this.invalidCount = invalidCount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HourlyStats that = (HourlyStats) o;
        return id == that.id &&
                requestCount == that.requestCount &&
                invalidCount == that.invalidCount &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, timestamp, requestCount, invalidCount, customer);
    }

    @Override
    public String toString() {
        return "HourlyStats{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", timestamp=" + timestamp +
                ", requestCount=" + requestCount +
                ", invalidCount=" + invalidCount +
                ", customer=" + customer +
                '}';
    }
}
