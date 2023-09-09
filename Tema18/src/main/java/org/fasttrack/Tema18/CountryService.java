package org.fasttrack.Tema18;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private List<Country> countries;

    public CountryService(List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getAllCountries() {
        return countries;
    }

    public String getCapitalById(String countryId) {
        Country country = findCountryById(countryId);
        return (country != null) ? country.getCapital() : null;
    }

    private Country findCountryById(String countryId) {
        return countries.stream()
                .filter(country -> country.getId().equals(countryId))
                .findFirst()
                .orElse(null);
    }

    public List<Country> getCountriesInContinent(String continentName) {
        return countries.stream()
                .filter(country -> continentName.equalsIgnoreCase(country.getContinent()))
                .collect(Collectors.toList());
    }

    public List<String> getCountryNeighborsById(String countryId) {
        Country country = findCountryById(countryId);
        return (country != null) ? country.getNeighbors() : null;
    }

    public List<Country> getCountriesInContinentWithPopulation(String continentName, long minPopulation) {
        return countries.stream()
                .filter(country -> continentName.equalsIgnoreCase(country.getContinent())
                        && country.getPopulation() >= minPopulation)
                .collect(Collectors.toList());
    }

    public List<Country> getCountriesByNeighbor(String includeNeighbor, String excludeNeighbor) {
        return countries.stream()
                .filter(country ->
                        country.getNeighbors().contains(includeNeighbor)
                                && !country.getNeighbors().contains(excludeNeighbor)
                )
                .collect(Collectors.toList());
    }


    public long getPopulationById(String countryId) {
        Country country = findCountryById(countryId);
        return (country != null) ? country.getPopulation() : null;
    }
}
