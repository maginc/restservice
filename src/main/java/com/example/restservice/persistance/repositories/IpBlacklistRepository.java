package com.example.restservice.persistance.repositories;

import com.example.restservice.persistance.model.IpBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpBlacklistRepository extends JpaRepository<IpBlacklist, Long> {
}
