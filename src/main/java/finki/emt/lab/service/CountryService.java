package finki.emt.lab.service;

import finki.emt.lab.model.Country;

import java.util.List;

public interface CountryService {

    public List<Country> listAll();
    public List<Country> listByContinent(String continent);
    public Country findById(Long id) throws Exception;
    public Country findByName(String name) throws Exception;
    public Country addCountry(Country country);
    public Country create(String name, String continent);
    public Country delete(Long id) throws Exception;
    public Country updateCountry(long id, String name, String continent) throws Exception;
}
