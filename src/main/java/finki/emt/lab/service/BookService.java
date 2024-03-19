package finki.emt.lab.service;

import finki.emt.lab.model.Author;
import finki.emt.lab.model.Book;
import finki.emt.lab.model.Category;

import java.util.List;

public interface BookService {



    List<Book> listBooks();
    Book findById(Long id) throws Exception;

    List<Book> findAllBooksByAuthor(Long authorId) throws Exception;

    Book addBook (Book book);
    Book createBook (String name, Category category, Long authorId, Integer avCopies) throws Exception;
    Book updateBook (Long id, String name, Category category, Long authorId, Integer avCopies) throws Exception;
    Book delete (Long id) throws Exception;
    Book borrowBook (Long id) throws Exception;
    Book returnBook (Long id) throws Exception;
}
