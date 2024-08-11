package com.healthcheck.gcc.persistance.repository;

import com.healthcheck.gcc.persistance.entity.Provider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Query("SELECT p FROM Provider p WHERE p.providerType.typeId = :typeId")
    List<Provider> findProvidersByType(@Param("typeId") Long typeId);
}
