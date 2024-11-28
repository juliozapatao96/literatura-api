package com.literaturagutendex.literaturagutendex.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class Book {
    private String title;
    private List<AuthorsData> authors;
    private List<String> languages;
    private Integer downloadCount;

    public Book(){}

    public Book(BooksData booksData){
        this.title = booksData.title();
        this.authors = booksData.authors();
        this.languages = booksData.languages();
        this.downloadCount = booksData.downloadCount();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorsData> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorsData> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return super.toString(); // PENDIENTE MODIFICAR
    }
}
