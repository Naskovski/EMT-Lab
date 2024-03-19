package finki.emt.lab.service;

import finki.emt.lab.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> listAuthors();
    Author findById(Long id) throws Exception;

    Author addAuthor (Author author);
    Author createAuthor (String name, String surname, String country) throws Exception;
    Author updateAuthor (Long id, String name, String surname, String country) throws Exception;
    Author delete (Long id) throws Exception;
}
