package finki.emt.lab.service.impl;

import finki.emt.lab.model.Author;
import finki.emt.lab.model.Book;
import finki.emt.lab.model.Category;
import finki.emt.lab.model.views.NumBooksPerCategoryView;
import finki.emt.lab.repository.BookRepository;
import finki.emt.lab.repository.NumBookPerCategoryViewRepository;
import finki.emt.lab.service.AuthorService;
import finki.emt.lab.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    private final NumBookPerCategoryViewRepository bpcRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, NumBookPerCategoryViewRepository bpcRepository) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bpcRepository = bpcRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) throws Exception {
        return bookRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public List<Book> findAllBooksByAuthor(Long authorId) throws Exception {
        Author author = authorService.findById(authorId);
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public Book createBook(String name, Category category, Long authorId, Integer avCopies) throws Exception {
        Book book = new Book(name, category, authorService.findById(authorId), avCopies);
        return addBook(book);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public Book updateBook(Long id, String name, Category category, Long authorId, Integer avCopies) throws Exception {
        Book book = findById(id);
        if(name!=null) book.setName(name);
        if(category!=null) book.setCategory(category);
        if(authorId!=null) book.setAuthor(authorService.findById(authorId));
        if(avCopies!=null) book.setAvailableCopies(avCopies);
        return bookRepository.save(book);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public Book delete(Long id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(Exception::new);
        bookRepository.delete(book);
        return book;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public Book borrowBook(Long id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(Exception::new);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        return book;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public Book returnBook(Long id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(Exception::new);
        book.setAvailableCopies(book.getAvailableCopies()+1);
        bookRepository.save(book);
        return book;
    }

    @Override
    public void refreshMaterializedView() {
       bpcRepository.refreshMaterializedView();
    }

    @Override
    public List<NumBooksPerCategoryView> getNumBooksPerCategory() {
        return bpcRepository.findAll();
    }
}
