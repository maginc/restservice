package com.example.restservice;

import com.example.restservice.persistance.model.Customer;
import com.example.restservice.persistance.model.IpBlacklist;
import com.example.restservice.persistance.model.UaBlacklist;
import com.example.restservice.persistance.repositories.CustomerRepository;
import com.example.restservice.persistance.repositories.IpBlacklistRepository;
import com.example.restservice.persistance.repositories.UaBlacklistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RestserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestserviceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demoData(CustomerRepository customerRepository,
//                                      IpBlacklistRepository ipBlacklistRepository,
//                                      UaBlacklistRepository uaBlacklistRepository){
//
//        return args -> {
//            List<UaBlacklist> userAgentList = Arrays.asList(
//                    new UaBlacklist("A6-Indexer"),
//                    new UaBlacklist("Googlebot-News"),
//                    new UaBlacklist("Googlebot")
//            );
//            uaBlacklistRepository.saveAll(userAgentList);
//
//            List<IpBlacklist> ipBlacklistList = Arrays.asList(
//                    new IpBlacklist(0),
//                    new IpBlacklist(2130706433),
//                    new IpBlacklist(4294967295L),
//                    new IpBlacklist(3232236149L)
//            );
//            ipBlacklistRepository.saveAll(ipBlacklistList);
//
//           List<Customer> customerList = Arrays.asList(
//                   new Customer("Big News Media Corp",1),
//                   new Customer("Online Mega Store", 1),
//                   new Customer("Nachoroo Delivery",0),
//                   new Customer("Euro Telecom Group", 1)
//                   );
//
//            customerRepository.saveAll(customerList);
//
//        };
//    }

}
