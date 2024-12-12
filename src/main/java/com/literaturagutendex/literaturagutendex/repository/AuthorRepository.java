package com.literaturagutendex.literaturagutendex.repository;

import com.literaturagutendex.literaturagutendex.model.Author;
import com.literaturagutendex.literaturagutendex.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    //Derived Queries
    Optional<Author> findByNameContainsIgnoreCase(String authorName);

//    @Query("SELECT s FROM Serie s WHERE s.totalSeasons <= :totalSeasons AND s.evaluation >= :evaluation") // JPQL
//    List<Serie> seriesBySeasonAndEvaluation(int totalSeasons, Double evaluation);
    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND a.deathYear >= :year")
    List<Author> authorsByYearTheyWereAlive(int year);
}
