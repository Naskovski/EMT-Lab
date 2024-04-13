package finki.emt.lab.repository;

import finki.emt.lab.model.Category;
import finki.emt.lab.model.views.NumBooksPerCategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NumBookPerCategoryViewRepository extends JpaRepository<NumBooksPerCategoryView, Category> {
    @Modifying
    @Query(value="create materialized view num_books_per_category as\n " +
            "select category, count(distinct id) as num_books\n" +
            "from book\n" +
            "group by category", nativeQuery = true)
    void createMaterializedView();

    @Modifying
    @Query(value="refresh materialized view num_books_per_category", nativeQuery = true)
    void refreshMaterializedView();
}
