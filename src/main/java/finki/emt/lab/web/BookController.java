package finki.emt.lab.web;

import finki.emt.lab.model.Author;
import finki.emt.lab.model.Book;
import finki.emt.lab.model.Category;
import finki.emt.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins = {"http://localhost:3000"})
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get/all")
    public List<Book> listAuthors (){
        return bookService.listBooks();
    }
    @GetMapping("/get/categories")
    public List<Category> listCategories (){
        return bookService.getAllCategories();
    }

    @GetMapping("/get/{id}")
    public Book getBook (@PathVariable Long id) throws Exception {
        return bookService.findById(id);
    }

    @PostMapping({"/update/{id}", "/edit/{id}"})
    public Book updateBook(@PathVariable Long id,
                           @RequestParam (required = false) String name,
                           @RequestParam (required = false) Category category,
                           @RequestParam (required = false) Long authorId,
                           @RequestParam (required = false) Integer numCopies) throws Exception {
        return bookService.updateBook(id, name, category, authorId, numCopies);
    }

    @PostMapping("/new")
    public Book createBook(@RequestParam String name,
                               @RequestParam Category category,
                               @RequestParam Long authorId,
                               @RequestParam Integer numCopies) throws Exception {

        return bookService.createBook(name, category, authorId, numCopies);
    }

    @PostMapping("/delete/{id}")
    public Book deleteBook(@PathVariable Long id) throws Exception {
        return bookService.delete(id);
    }

    @PostMapping("/borrow/{id}")
    public Book borrowBook(@PathVariable Long id) throws Exception {
        return bookService.borrowBook(id);
    }

    @PostMapping("/return/{id}")
    public Book returnBook(@PathVariable Long id) throws Exception {
        return bookService.returnBook(id);
    }


}
