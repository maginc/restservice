package com.example.restservice.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Andris Magins
 * @created 21-Jul-20
 **/
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @Column
    private String name;

    @NonNull
    @Column
    private int active = 1;



    @Column
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<HourlyStats> hourlyStats;

    public Customer() {
    }

    public Customer(String name, int active) {
        this.name = name;
        this.active = active;
    }
    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<HourlyStats> getHourlyStats() {
        return hourlyStats;
    }

    public void setHourlyStats(List<HourlyStats> hourlyStats) {
        this.hourlyStats = hourlyStats;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", hourlyStats=" + hourlyStats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                active == customer.active &&
                name.equals(customer.name) &&
                Objects.equals(hourlyStats, customer.hourlyStats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active, hourlyStats);
    }
}
