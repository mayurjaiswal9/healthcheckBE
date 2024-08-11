package com.healthcheck.gcc.service;

import com.healthcheck.gcc.persistance.entity.Provider;
import com.healthcheck.gcc.persistance.entity.ProviderType;
import com.healthcheck.gcc.persistance.repository.ProviderRepository;
import com.healthcheck.gcc.persistance.repository.ProviderTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderTypeRepository providerTypeRepository;

    public ProviderType addProviderType(ProviderType providerType) {
        return providerTypeRepository.save(providerType);
    }

    public Provider addProvider(Provider provider) {
        Optional<ProviderType> providerType = providerTypeRepository.findById(provider.getProviderType().getTypeId());
        if (providerType.isPresent()) {
            provider.setProviderType(providerType.get());
            return providerRepository.save(provider);
        } else {
            throw new RuntimeException("ProviderType not found with id " + provider.getProviderType().getTypeId());
        }
    }

    public List<ProviderType> getAllProviderTypes() {
        return providerTypeRepository.findAll();
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }
}
