package finki.emt.lab.repository;

import finki.emt.lab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    public List<Country> findAllByContinentLikeIgnoreCase(String continent);
    public Optional<Country> findByNameLikeIgnoreCase(String name);
}
