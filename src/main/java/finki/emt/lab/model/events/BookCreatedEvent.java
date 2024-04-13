package finki.emt.lab.model.events;

import finki.emt.lab.model.Book;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class BookCreatedEvent extends ApplicationEvent {

    private LocalDateTime timestamp;

    public BookCreatedEvent(Book source) {
        super(source);
        this.timestamp = LocalDateTime.now();
    }

}
