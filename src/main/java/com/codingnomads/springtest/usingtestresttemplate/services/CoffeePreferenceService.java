/* CodingNomads (C)2024 */
package com.codingnomads.springtest.usingtestresttemplate.services;

import com.codingnomads.springtest.usingtestresttemplate.models.CoffeePreference;
import com.codingnomads.springtest.usingtestresttemplate.repos.CoffeePreferenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoffeePreferenceService {

    @Autowired
    private CoffeePreferenceRepo repo;

    public CoffeePreference insertNewCoffeePreference(CoffeePreference coffeePreference) {
        return repo.save(coffeePreference);
    }

    public CoffeePreference getCoffeePreference(Long id) throws Exception {
        Optional<CoffeePreference> preferenceOptional = repo.findById(id);
        if (preferenceOptional.isEmpty()) {
            throw new Exception("Coffee preference not found.");
        }
        return preferenceOptional.get();
    }
}
