package finki.emt.lab.model.views;

import finki.emt.lab.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Subselect("SELECT * FROM public.num_books_per_category")
@Entity
@Immutable
public class NumBooksPerCategoryView {

    @Id
    @Column(name = "category")
    private Category category;

    @Column(name = "num_books")
    private Integer num_books;
}
