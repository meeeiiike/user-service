package ie.atu.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ie.atu.user_service.model.User;
import ie.atu.user_service.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void getUserById_returns200() throws Exception {
        User user = User.builder()
                .id(1L)
                .userID("U001")
                .email("meike@gmail.com")
                .password("1234")
                .build();

        when(userService.getUserById("U001")).thenReturn(user);
        mockMvc.perform(get("/api/user/callUserService/{userId}","U001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userID").value("U001"))
                .andExpect(jsonPath("$.email").value("meike@gmail.com"));
    }

    @Test
    void getAllUsers_returns200() throws Exception {
        User user1 = User.builder()
                .userID("U001")
                .email("meike@gmail.com")
                .password("1234")
                .build();
        User user2 = User.builder()
                .userID("U002")
                .email("notMeike@gmail.com")
                .password("4321")
                .build();
        List<User> users = List.of(user1, user2);
        when(userService.findAll()).thenReturn(users);
        mockMvc.perform(get("/api/user/returnAllUsers"))
                .andExpect(status().isOk());
    }
/*
    @Test
    void deleteBookById_returns200() throws Exception {

        mockMvc.perform(delete("/book/deleteBook/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateBookById_returns200() throws Exception {
        Book book = Book.builder()
                .bookID(1L)
                .name("Harry Potter")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();



    }


    @Test
    void createBook_returns201() throws Exception {

        Book requestBook = Book.builder()
                .name("Harry Potter")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();

        Book savedBook = Book.builder()
                .bookID(1L)
                .name("Harry Potter")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();

        when(bookService.create(any(Book.class))).thenReturn(savedBook);

        ObjectMapper mapper = new ObjectMapper();
        String jsonValue = mapper.writeValueAsString(requestBook);

        mockMvc.perform(post("/book/createBook")
                        .contentType("application/json")
                        .content(jsonValue))
                .andExpect(status().isCreated())
                .andExpect(content().string("Book created with id 1"));
    }


    @Test
    void updateBook_returns204() throws Exception {

        Book book = Book.builder()
                .bookID(1L)
                .name("Harry Potter")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();

        Book updatedBook = Book.builder()
                .bookID(1L)
                .name("Harry Potter Two")
                .author("JK Rowling")
                .publisher("Bloomsbury")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonValue = mapper.writeValueAsString(book);

        mockMvc.perform(put("/book/updateBook/1")
                .contentType("application/json")
                .content(jsonValue))
                .andExpect(status().isOk())
                .andExpect(content().string("Book updated with id 1"));

    }

 */
}