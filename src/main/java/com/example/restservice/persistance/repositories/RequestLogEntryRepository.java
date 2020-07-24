package com.example.restservice.persistance.repositories;

import com.example.restservice.persistance.model.RequestLogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RequestLogEntryRepository extends JpaRepository<RequestLogEntry, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM RequestLogEntry requestLogEntry")
    void deleteAllWithQuery();

    List<RequestLogEntry> findAllByCustomerId(int customerId);

}
