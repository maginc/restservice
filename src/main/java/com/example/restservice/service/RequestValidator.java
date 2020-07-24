package com.example.restservice.service;

import com.example.restservice.persistance.model.Customer;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Andris Magins
 * @created 24-Jul-20
 **/
public class RequestValidator {

        private HttpServletRequest request;
        private Optional<Customer> customer;
        private HttpStatus status;
        private String message;
        private boolean isValid;
        private int customerId;
        private BlackListFilters blackListFilters;

        public RequestValidator(int customerId, BlackListFilters blackListFilters, HttpServletRequest request,
                                Optional<Customer> customer) {
            this.request = request;
            this.customer = customer;
            this.blackListFilters = blackListFilters;
            this.customerId = customerId;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public boolean isValid() {
            return isValid;
        }

        public RequestValidator invoke() {
            if(customer.isEmpty()){
                status = HttpStatus.BAD_REQUEST;
                message = "Customer not found";
            }else if (customer.get().getId() != customerId) {
                status = HttpStatus.BAD_REQUEST;
                message = "Customer id differed from body customer id";
            } else if (blackListFilters.isIpBlacklisted(request) || blackListFilters.isUserAgentBlacklisted(request)) {
                status = HttpStatus.BAD_REQUEST;
                message = "IP address or user agent is blacklisted";
            } else if ((customer.get().getId() == 0)) {
                status = HttpStatus.BAD_REQUEST;
                message = "Customer not found";
            } else if ((customer.get().getActive() == 0)) {
                status = HttpStatus.BAD_REQUEST;
                message = "Customer is disabled";
            } else {
                this.isValid = true;
                status = HttpStatus.OK;
                message = "Request received successfully";
            }
            return this;
        }

}
