package com.example.jtweet.author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void given_lots_of_author_to_repository_should_return_list_of_author_200() throws Exception {
        List<Author> mockAuthorList = new ArrayList<>();
        mockAuthorList.add(
            new Author("Bruce", 11)
        );

        mockAuthorList.add(
            new Author("Cindy", 13)
        );

        Mockito.doReturn(mockAuthorList).when(repository).getAuthorList();

        String expectedResponse = objectMapper.writeValueAsString(mockAuthorList);

        this.mockMvc.perform(get("/authors")).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(expectedResponse));

        verify(repository).getAuthorList();
    }

    @Test
    void given_no_author_to_repository_should_return_empty_list_200() throws Exception {
        List<?> emptyList = new ArrayList<>();

        Mockito.doReturn(emptyList).when(repository).getAuthorList();

        String expectedResponse = objectMapper.writeValueAsString(emptyList);

        this.mockMvc.perform(get("/authors")).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(expectedResponse));

        verify(repository).getAuthorList();
    }

    @Test
    void given_one_author_should_return_single_author_200() throws Exception {
        Author mockAuthor = new Author("Bruce", 12);

        Mockito.doReturn(Optional.ofNullable(mockAuthor)).when(repository).getAuthor();

        String expectedResponse = objectMapper.writeValueAsString(mockAuthor);

        this.mockMvc.perform(get("/authors/123")).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(expectedResponse));

        verify(repository).getAuthor();
    }

    @Test
    void given_no_author_should_return_no_author_404() throws Exception {
        Mockito.doReturn(Optional.empty()).when(repository).getAuthor();

        when(repository.getAuthor()).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/authors/123")).andDo(print())
            .andExpect(status().isNotFound());

        verify(repository).getAuthor();
    }
}
