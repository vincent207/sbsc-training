package com.sgi.springtraining.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgi.springtraining.rest.controller.BookController;
import com.sgi.springtraining.rest.model.Book;
import com.sgi.springtraining.rest.model.User;
import com.sgi.springtraining.rest.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreateBookSuccess() throws Exception {
        Book newBook = new Book("Semana Santa di Larantuka", Long.valueOf(15000));

        //given(userService.createUser(any(User.class))).willReturn();

        String res = mockMvc.perform(post("/api/user")
                        .content(mapper.writeValueAsString(newBook))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        User responseUser = mapper.readValue(res, User.class);
        assertEquals(newBook, responseUser);
    }
}
