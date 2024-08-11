package com.healthcheck.gcc.persistance.repository;

import com.healthcheck.gcc.persistance.entity.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderTypeRepository extends JpaRepository<ProviderType, Long> {
}