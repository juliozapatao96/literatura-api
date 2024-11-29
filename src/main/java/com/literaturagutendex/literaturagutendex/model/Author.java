package com.literaturagutendex.literaturagutendex.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Author {

    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public Author(){}

    public Author(AuthorsData authorsData){
        this.name = authorsData.name();
        this.birthYear = authorsData.birthYear();
        this.deathYear = authorsData.deathYear();
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

    @Override
    public String toString() {
        return
                "Nombre= "+ name + ", Año de Nacimiento= "+birthYear+
                        ", Año de Muerte= "+deathYear;
    }
}
