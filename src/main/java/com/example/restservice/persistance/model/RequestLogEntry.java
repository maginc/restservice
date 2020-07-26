package com.example.restservice.persistance.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Andris Magins
 * @created 23-Jul-20
 **/
@Entity
public class RequestLogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time")
    private Date createDate;

    private int customerId;
    private boolean isValid;

    public RequestLogEntry() {
    }

    public RequestLogEntry(int customerId, boolean isValid) {
        this.customerId = customerId;
        this.isValid = isValid;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return "RequestLogEntry{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", customerId=" + customerId +
                ", isValid=" + isValid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLogEntry that = (RequestLogEntry) o;
        return id == that.id &&
                customerId == that.customerId &&
                isValid == that.isValid &&
                Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, customerId, isValid);
    }
}
