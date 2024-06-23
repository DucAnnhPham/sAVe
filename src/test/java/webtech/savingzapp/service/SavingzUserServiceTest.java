package webtech.savingzapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import webtech.savingzapp.model.Savingz_User;
import webtech.savingzapp.persistance.SavingzUserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SavingzUserServiceTest {

    @Autowired
    private SavingzUserService savingzUserService;

    @MockBean
    private SavingzUserRepository savingzUserRepository;

    @MockBean
    private PasswordHashingService passwordHashingService;

    @Test
    void testRegisterUser() {
        //Eingabedaten
        Savingz_User newSavingzUser = new Savingz_User();
        newSavingzUser.setUsername("TestUser");
        newSavingzUser.setPassword("TestPassword");
        newSavingzUser.setEmail("testmail");

        //Mocking
        when(savingzUserRepository.findByUsername(newSavingzUser.getUsername())).thenReturn(null);
        when(savingzUserRepository.findByEmail(newSavingzUser.getEmail())).thenReturn(null);
        when(passwordHashingService.hashPassword(newSavingzUser.getPassword())).thenReturn("hashedPassword");
        when(savingzUserRepository.save(newSavingzUser)).thenReturn(newSavingzUser);

        //erwartetes Ergebnis
        ResponseEntity<Savingz_User> response = savingzUserService.register(newSavingzUser);

        //Vergleich
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newSavingzUser, response.getBody());
    }

    @Test
    void testLoginUser() {

        //Eingabedaten
        Savingz_User existingSavingzUser = new Savingz_User();
        existingSavingzUser.setUsername("TestUser");
        existingSavingzUser.setPassword("hashedPassword");

        //Mocking
        when(savingzUserRepository.findByUsername(existingSavingzUser.getUsername())).thenReturn(existingSavingzUser);
        when(passwordHashingService.checkPassword("TestPassword", existingSavingzUser.getPassword())).thenReturn(true);

        //erwartetes Ergebnis
        Savingz_User response = savingzUserService.login(existingSavingzUser.getUsername(), "TestPassword");

        //Vergleich
        assertEquals(existingSavingzUser, response);
    }


}