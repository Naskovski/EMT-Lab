package finki.emt.lab.web;

import finki.emt.lab.model.Country;
import finki.emt.lab.service.CountryService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/get/cont/{continent}")
    public List<Country> getList(@PathVariable String continent){
        if(continent.equals("all")) return countryService.listAll();
        return countryService.listByContinent(continent);
    }

    @GetMapping("/get/name/{name}")
    public Country getByName(@PathVariable String name) throws Exception {
        return countryService.findByName(name);
    }

    @GetMapping("/get/id/{id}")
    public Country getCountry (@PathVariable Long id) throws Exception {
        return countryService.findById(id);
    }

    @PostMapping("/new")
    public Country create(@RequestParam String name,
                          @RequestParam String continent) throws Exception {
        return countryService.create(name, continent);
    }

    @PostMapping("/delete/{id}")
    public Country delete(@PathVariable Long id) throws Exception {
        return countryService.delete(id);
    }

    @PostMapping({"/update/{id}", "/edit/{id}"})
    public Country updateCountry(@PathVariable Long id,
                               @RequestParam (required = false) String name,
                               @RequestParam (required = false) String continent) throws Exception {
        return countryService.updateCountry(id, name, continent);
    }




}
