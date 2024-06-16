package com.example.jtweet.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors/{authorId}")
    public ResponseEntity<Author> RetrieveAuthorById(@PathVariable String authorId) {
        Author fakeAuthor = authorRepository.getAuthor();
        return new ResponseEntity<>(fakeAuthor, HttpStatus.OK);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> RetrieveAuthorList() {
        List<Author> fakeAuthors = authorRepository.getAuthorList();

        return new ResponseEntity<>(fakeAuthors, HttpStatus.OK);
    }
}
