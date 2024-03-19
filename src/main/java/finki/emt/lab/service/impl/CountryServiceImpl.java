package finki.emt.lab.service.impl;

import finki.emt.lab.model.Country;
import finki.emt.lab.repository.CountryRepository;
import finki.emt.lab.service.CountryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public List<Country> listByContinent(String continent) {
        return countryRepository.findAllByContinentLikeIgnoreCase(continent);
    }

    @Override
    public Country findById(Long id) throws Exception {
        return countryRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Country findByName(String name) throws Exception {
        return countryRepository.findByNameLikeIgnoreCase(name).orElseThrow(Exception::new);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Country create(String name, String continent) {
            Country country = new Country(name, continent);
        return addCountry(country);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Country delete(Long id) throws Exception {
        Country country = countryRepository.findById(id).orElseThrow(Exception::new);
        countryRepository.delete(country);
        return country;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Country updateCountry(long id, String name, String continent) throws Exception {
        Country country = countryRepository.findById(id).orElseThrow(Exception::new);
        if(name!=null) country.setName(name);
        if(continent!=null) country.setContinent(name);
        return countryRepository.save(country);
    }


}
