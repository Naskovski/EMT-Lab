package finki.emt.lab.web;

import finki.emt.lab.model.Author;
import finki.emt.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    public final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/get/all")
    public List<Author> listAuthors (){
        return authorService.listAuthors();
    }

    @GetMapping("/get/{id}")
    public Author getAuthor (@PathVariable Long id) throws Exception {
        return authorService.findById(id);
    }

    @PostMapping({"/update/{id}", "/edit/{id}"})
    public Author updateAuthor(@PathVariable Long id,
                               @RequestParam (required = false) String name,
                               @RequestParam (required = false) String surname,
                               @RequestParam (required = false) String country) throws Exception {
        return authorService.updateAuthor(id, name, surname, country);
    }

    @PostMapping("/new")
    public Author createAuthor(@RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String country) throws Exception {
        return authorService.createAuthor(name, surname, country);
    }

    @PostMapping("/delete/{id}")
    public Author deleteAuthor(@PathVariable Long id) throws Exception {
        return authorService.delete(id);
    }

}
