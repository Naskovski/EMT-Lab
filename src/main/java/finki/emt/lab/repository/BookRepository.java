package finki.emt.lab.repository;

import finki.emt.lab.model.Author;
import finki.emt.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> findAllByAuthor(Author author);
}
