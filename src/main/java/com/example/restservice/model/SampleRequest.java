package com.example.restservice.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customerID",
        "tagID",
        "userID",
        "remoteIP",
        "timestamp"
})
public class SampleRequest {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty("customerID")
    @NotNull
    private int customerID;

    @JsonProperty("tagID")
    @NotNull
    private Long tagID;

    @JsonProperty("userID")
    @NotNull
    private String userID;

    /*
                Even if ip address is present making sure its in valid form and range
     */
    @JsonProperty("remoteIP")
    @NotNull
    @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")
    private String remoteIP;

    @JsonProperty("timestamp")
    @NotNull
    private Long timestamp;

    @JsonProperty("customerID")
    public int getCustomerID() {
        return customerID;
    }

    @JsonProperty("customerID")
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @JsonProperty("tagID")
    public Long getTagID() {
        return tagID;
    }

    @JsonProperty("tagID")
    public void setTagID(Long tagID) {
        this.tagID = tagID;
    }

    @JsonProperty("userID")
    public String getUserID() {
        return userID;
    }

    @JsonProperty("userID")
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @JsonProperty("remoteIP")
    public String getRemoteIP() {
        return remoteIP;
    }

    @JsonProperty("remoteIP")
    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleRequest that = (SampleRequest) o;
        return Objects.equals(customerID, that.customerID) &&
                Objects.equals(tagID, that.tagID) &&
                Objects.equals(userID, that.userID) &&
                Objects.equals(remoteIP, that.remoteIP) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, tagID, userID, remoteIP, timestamp);
    }

    @Override
    public String toString() {
        return "SampleRequest{" +
                "customerID=" + customerID +
                ", tagID=" + tagID +
                ", userID='" + userID + '\'' +
                ", remoteIP='" + remoteIP + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}