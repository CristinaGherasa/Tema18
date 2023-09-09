package org.fasttrack.Tema18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Tema18Application {
	public static void main(String[] args) {
		SpringApplication.run(Tema18Application.class, args);
	}

	@Bean
	public CountryService countryService() throws IOException {
		List<Country> countries = new CountryReader().readCountriesFromFile();
		return new CountryService(countries);
	}

}
