package com.healthcheck.gcc.controller;

import com.healthcheck.gcc.persistance.entity.Provider;
import com.healthcheck.gcc.persistance.entity.ProviderType;
import com.healthcheck.gcc.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping("/types")
    public ResponseEntity<ProviderType> addProviderType(@RequestBody ProviderType providerType) {
        ProviderType createdProviderType = providerService.addProviderType(providerType);
        return ResponseEntity.ok(createdProviderType);
    }

    @PostMapping
    public ResponseEntity<Provider> addProvider(@RequestBody Provider provider) {
        Provider createdProvider = providerService.addProvider(provider);
        return ResponseEntity.ok(createdProvider);
    }

    @GetMapping("/types")
    public ResponseEntity<List<ProviderType>> getAllProviderTypes() {
        List<ProviderType> providerTypes = providerService.getAllProviderTypes();
        return ResponseEntity.ok(providerTypes);
    }

    @GetMapping
    public ResponseEntity<List<Provider>> getAllProviders() {
        List<Provider> providers = providerService.getAllProviders();
        return ResponseEntity.ok(providers);
    }
}
