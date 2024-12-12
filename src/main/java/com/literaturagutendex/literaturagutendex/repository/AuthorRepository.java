package com.literaturagutendex.literaturagutendex.repository;

import com.literaturagutendex.literaturagutendex.model.Author;
import com.literaturagutendex.literaturagutendex.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    //Derived Queries
    Optional<Author> findByNameContainsIgnoreCase(String authorName);
}
