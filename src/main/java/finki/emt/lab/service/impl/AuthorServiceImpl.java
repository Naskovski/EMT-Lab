package finki.emt.lab.service.impl;

import finki.emt.lab.model.Author;
import finki.emt.lab.repository.AuthorRepository;
import finki.emt.lab.service.AuthorService;
import finki.emt.lab.service.CountryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;


    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }


    @Override
    public Author findById(Long id) throws Exception {
        return authorRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public Author createAuthor(String name, String surname, String country) throws Exception {
        Author author = new Author(name, surname, countryService.findByName(country));
        return addAuthor(author);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public Author updateAuthor(Long id, String name, String surname, String country) throws Exception {
        Author author = authorRepository.findById(id).orElseThrow(Exception::new);
        if(country!=null) author.setCountry(countryService.findByName(country));
        if(name!=null) author.setName(name);
        if(surname!=null) author.setSurname(surname);
        authorRepository.save(author);
        return author;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public Author delete(Long id) throws Exception {
        Author author = authorRepository.findById(id).orElseThrow(Exception::new);
        authorRepository.delete(author);
        return author;
    }
}
