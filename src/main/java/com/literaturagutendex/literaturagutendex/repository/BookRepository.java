package com.literaturagutendex.literaturagutendex.repository;

import com.literaturagutendex.literaturagutendex.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    //Derived Queries
    Optional<Book> findByTitleContainsIgnoreCase(String bookTitle);

    List<Book> findByLanguage(String language);

//    @Query("SELECT s FROM Serie s WHERE s.totalSeasons <= :totalSeasons AND s.evaluation >= :evaluation") // JPQL
//    List<Serie> seriesBySeasonAndEvaluation(int totalSeasons, Double evaluation);

}
