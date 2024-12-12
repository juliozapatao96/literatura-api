package com.literaturagutendex.literaturagutendex.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String title;
    private String language;
    private Integer downloadCount;
    //@Transient // Anotación que sirve para ignorar las relaciones por el momento
    @ManyToOne
    private Author author;
    @Transient
    private Author authorTemp;

    public Book(){} // Constructor requerido por JPA

    public Book(BooksData booksData){
        this.title = booksData.title();
        this.authorTemp = new Author(booksData.authors().getFirst());
        this.language = booksData.languages().getFirst();
        this.downloadCount = booksData.downloadCount();
    }

    public Author getAuthorTemp() {
        return authorTemp;
    }

    public void setAuthorTemp(Author authorTemp) {
        this.authorTemp = authorTemp;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguage() { return language; }

    public void setLanguage(String language) { this.language = language; }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return
                "Titulo= "+ title + ", Autor= "+author.getName()+
                ", Lenguaje= "+language+ ", Descargas= "+downloadCount;
    }
}
