package com.example.restservice.persistance.model;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Andris Magins
 * @created 21-Jul-20
 **/
@Entity
@Table(name = "ua_blacklist")
public class UaBlacklist {
    @Id
    @NonNull
    private String ua;
    public UaBlacklist(){

    }

    public UaBlacklist(String ua){
    this.ua = ua;
    }

    @NonNull
    public String getUa() {
        return ua;
    }

    public void setUa(@NonNull String ua) {
        this.ua = ua;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UaBlacklist that = (UaBlacklist) o;
        return ua.equals(that.ua);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ua);
    }

    @Override
    public String toString() {
        return "UaBlacklist{" +
                "ua='" + ua + '\'' +
                '}';
    }
}
