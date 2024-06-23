package webtech.savingzapp.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import webtech.savingzapp.model.Savingz_User;
import webtech.savingzapp.service.SavingzUserService;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SavingzUserController.class)
class SavingzUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SavingzUserService service;



    private Savingz_User newUser;
    private Savingz_User existingUser;

    @BeforeEach
    void setUp() {
        existingUser = new Savingz_User("testUser", "test@example.com", "password123");
        existingUser.setId(1L);
        newUser = new Savingz_User("testUser", "test@example.com", "password123");
        newUser.setId(2L);
    }

    @Test
    void testRegisterUser() throws Exception {

        //Mocking
        when(service.register(any(Savingz_User.class))).thenReturn(new ResponseEntity<>(newUser, HttpStatus.CREATED));

        //Ausführung des Test mit erwarteten Ergebnissen vergleichen
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testUser\", \"email\": \"test@example.com\", \"password\": \"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Registration successful"));
    }

    @Test
    void testRegisterUserBadRequest() throws Exception {

        //Mocking
        doThrow(new RuntimeException("Registration error")).when(service).register(any(Savingz_User.class));

        //Ausführung des Test mit erwarteten Ergebnissen vergleichen
        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"\", \"email\": \"test@example.com\", \"password\": \"password123\"}"))  // Assuming empty username causes error
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Registration error"));
    }

    @Test
    void testLoginUser() throws Exception {

        //Mocking
        when(service.login("testUser", "password123")).thenReturn(existingUser);

        //Ausführung des Test mit erwarteten Ergebnissen vergleichen
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"testUser\", \"password\": \"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));  // Assuming user ID is 1
    }

    @Test
    void testLoginUserUnauthorized() throws Exception {

        //Mocking
        when(service.login("testUser", "wrongPassword")).thenReturn(null);

        //Ausführung des Test mit erwarteten Ergebnissen vergleichen
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"testUser\", \"password\": \"wrongPassword\"}"))
                .andExpect(status().isUnauthorized());
    }



  
}