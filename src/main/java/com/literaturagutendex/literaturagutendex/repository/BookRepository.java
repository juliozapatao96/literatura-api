package com.literaturagutendex.literaturagutendex.repository;

import com.literaturagutendex.literaturagutendex.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
