package org.fasttrack.Tema18;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CountryReader {
    private static final String FILE_PATH = "countries.txt";

    public List<Country> readCountriesFromFile() throws IOException {
        List<Country> countries = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length >= 5) {
                    Country country = new Country();

                    String uniqueId = UUID.randomUUID().toString();
                    country.setId(uniqueId);

                    country.setName(parts[0]);
                    country.setCapital(parts[1]);
                    country.setPopulation(Long.parseLong(parts[2]));
                    country.setArea(Long.parseLong(parts[3]));
                    country.setContinent(parts[4]);

                    String[] neighbors = parts[5].split("~");
                    country.setNeighbors(List.of(neighbors));

                    countries.add(country);
                }
            }
        }
        return countries;
    }
}
