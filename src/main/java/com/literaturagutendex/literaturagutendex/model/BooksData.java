package com.literaturagutendex.literaturagutendex.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksData(
    @JsonAlias("count") Integer count,
    @JsonAlias("next") String next) {
}
