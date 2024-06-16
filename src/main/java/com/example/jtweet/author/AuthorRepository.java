package com.example.jtweet.author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository {
    private List<Author> authors = new ArrayList<>();

    public Optional<Author> getAuthor() {
        Author author = new Author("Andy", 12);
        return Optional.ofNullable(author);
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
