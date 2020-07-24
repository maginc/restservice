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
@Table(name = "ip_blacklist")
public class IpBlacklist {

    @Id
    @NonNull
    private long ip;

    public IpBlacklist() {
    }
    public IpBlacklist(long ip) {
        this.ip = ip;
    }

    public long getIp() {
        return ip;
    }

    public void setIp(long ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpBlacklist that = (IpBlacklist) o;
        return ip == that.ip;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }

    @Override
    public String toString() {
        return "IpBlacklist{" +
                "ip=" + ip +
                '}';
    }
}
