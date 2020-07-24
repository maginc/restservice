package com.example.restservice.persistance.repositories;

import com.example.restservice.persistance.model.UaBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UaBlacklistRepository extends JpaRepository<UaBlacklist, Long> {
}
