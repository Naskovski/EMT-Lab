package finki.emt.lab.listener;

import finki.emt.lab.model.events.BookCreatedEvent;
import finki.emt.lab.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventHandler {
    private final BookService bookService;

    public BookEventHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void onBookCreated(BookCreatedEvent event){
        System.out.println("BOOK CREATED DETECTED");
        bookService.refreshMaterializedView();
    }
}
