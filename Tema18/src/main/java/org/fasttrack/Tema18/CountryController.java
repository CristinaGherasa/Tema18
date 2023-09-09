package org.fasttrack.Tema18;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/countries")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/names")
    public List<String> getAllCountryNames() {
        return countryService.getAllCountries().stream()
                .map(Country::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/{countryId}/capital")
    public String getCapital(@PathVariable String countryId) {
        return countryService.getCapitalById(countryId);
    }

    @GetMapping("/{countryId}/population")
    public long getPopulation(@PathVariable String countryId) {
        return countryService.getPopulationById(countryId);
    }

    @GetMapping("/continents/{continentName}/countries")
    public List<Country> getCountriesInContinent(@PathVariable String continentName) {
        return countryService.getCountriesInContinent(continentName);
    }

    @GetMapping("/{countryId}/neighbours")
    public List<String> getCountryNeighbors(@PathVariable String countryId) {
        return countryService.getCountryNeighborsById(countryId);
    }

    @GetMapping("/continents/{continentName}/countries")
    public List<Country> getCountriesInContinentWithPopulation(
            @PathVariable String continentName,
            @RequestParam(name = "minPopulation") Long minPopulation
    ) {
        return countryService.getCountriesInContinentWithPopulation(continentName, minPopulation);
    }

    @GetMapping(params = {"includeNeighbour", "excludeNeighbour"})
    public List<Country> getCountriesByNeighbor(
            @RequestParam(name = "includeNeighbour") String includeNeighbor,
            @RequestParam(name = "excludeNeighbour") String excludeNeighbor
    ) {
        return countryService.getCountriesByNeighbor(includeNeighbor, excludeNeighbor);
    }

}
