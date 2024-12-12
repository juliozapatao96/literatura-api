package com.literaturagutendex.literaturagutendex.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@Transient
    private List<Book> books;


    public Author(){}

    public Author(AuthorsData authorsData){
        this.name = authorsData.name();
        this.birthYear = authorsData.birthYear();
        this.deathYear = authorsData.deathYear();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        books.forEach(b -> b.setAuthor(this)); // A cada libro estamos agregando el author a la que pertenecen
        this.books = books;
    }

    @Override
    public String toString() {
        return
                "Nombre= "+ name + ", Año de Nacimiento= "+birthYear+
                        ", Año de Muerte= "+deathYear;
    }
}
