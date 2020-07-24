package com.example.restservice;

import com.example.restservice.model.*;
import com.example.restservice.persistance.model.Customer;
import com.example.restservice.persistance.model.HourlyStats;
import com.example.restservice.persistance.model.RequestLogEntry;
import com.example.restservice.persistance.repositories.CustomerRepository;
import com.example.restservice.persistance.repositories.HourlyStatsRepository;
import com.example.restservice.persistance.repositories.RequestLogEntryRepository;
import com.example.restservice.service.BlackListFilters;
import com.example.restservice.service.HourlyStatsService;
import com.example.restservice.service.RequestValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1")
public class RestController {

    final
    BlackListFilters blackListFilters;
    final
    CustomerRepository customerRepository;
    final HourlyStatsRepository hourlyStatsRepository;
    final RequestLogEntryRepository requestLogEntryRepository;
    final HourlyStatsService hourlyStatsService;

    public RestController(BlackListFilters blackListFilters,
                          CustomerRepository customerRepository,
                          HourlyStatsRepository hourlyStatsRepository,
                          RequestLogEntryRepository requestLogEntryRepository, HourlyStatsService hourlyStatsService) {
        this.blackListFilters = blackListFilters;
        this.customerRepository = customerRepository;
        this.hourlyStatsRepository = hourlyStatsRepository;
        this.requestLogEntryRepository = requestLogEntryRepository;
        this.hourlyStatsService = hourlyStatsService;
    }


    @PostMapping(value = "/request/{customerId}", consumes = "application/json")
    public ResponseEntity<String> request(@PathVariable("customerId") int customerId,
                                          @Valid @RequestBody SampleRequest sampleRequest,
                                          HttpServletRequest request) {

        HttpStatus status;
        String message;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestValidator requestValidator = new RequestValidator(
                customerId,
                blackListFilters, request,
                customerRepository.findById(sampleRequest.getCustomerID())
        ).invoke();

        status = requestValidator.getStatus();
        message = requestValidator.getMessage();

        if (requestValidator.isValid()) {
            stubFunctionForProcessing(sampleRequest);
            requestLogEntryRepository.save(new RequestLogEntry(customerId, true));
        } else {
            requestLogEntryRepository.save(new RequestLogEntry(customerId, false));
        }

        return new ResponseEntity<>(message, headers, status);
    }

    @GetMapping(value = "/stats/{customerId}/{date}")
    public ResponseEntity<StatsResponse> stats(
            @PathVariable("customerId") int id,
            @PathVariable("date") String date) throws ParseException {

        HttpStatus status;
        status = HttpStatus.OK;
        Optional<Customer> customer = customerRepository.findById(id);

        StatsResponse statsResponse = new StatsResponse();
        if (customer.isPresent()) {

            statsResponse = getStatsResponse(date, customer.get());

        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(statsResponse, headers, status);
    }

    @GetMapping(value = "/toster")
    public void toster() {
        hourlyStatsService.scheduleFixedRateTask();
    }

    void stubFunctionForProcessing(SampleRequest sampleRequest) {
    }

    private StatsResponse getStatsResponse(String date, Customer customer) throws ParseException {

        Date requestedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        List<HourlyStats> hourlyStatsList =
                hourlyStatsRepository.findAllByCreateDateAndCustomer(
                requestedDate, customer);

        long validResponses = 0;
        long invalidResponses = 0;
        for (HourlyStats hourlyStats : hourlyStatsList) {
            invalidResponses += hourlyStats.getInvalidCount();
            validResponses += hourlyStats.getRequestCount();
        }
        return new StatsResponseBuilder()
                .setCustomer(customer)
                .setRequestedDate(date)
                .setTotalRequest(invalidResponses + validResponses)
                .setTotalInvalid(invalidResponses)
                .setTotalValid(validResponses)
                .setHourlyStats(hourlyStatsList)
                .createStatsResponse();

    }


}
