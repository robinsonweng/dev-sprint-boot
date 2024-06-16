package com.example.jtweet.author;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void retrieve_author_list_should_return_list_of_author() throws Exception {
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
    void retrieve_author_by_id_should_return_single_author() throws Exception {
        Author mockAuthor = new Author("Bruce", 12);

        Mockito.doReturn(mockAuthor).when(repository).getAuthor();

        String expectedResponse = objectMapper.writeValueAsString(mockAuthor);

        when(repository.getAuthor()).thenReturn(mockAuthor);

        this.mockMvc.perform(get("/authors/123")).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(expectedResponse));

        verify(repository).getAuthor();
    }
}
