package com.example.jtweet.author;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository {
    private List<Author> authors = new ArrayList<>();

    public Author getAuthor() {
        return new Author("Andy", 12);
    }
    public List<Author> getAuthorList() {
        authors.add(
            new Author("Bruce", 11)
        );

        authors.add(
            new Author("Cindy", 13)
        );

        return authors;
    }
}
