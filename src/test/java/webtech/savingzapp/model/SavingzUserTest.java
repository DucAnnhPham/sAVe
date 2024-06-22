package webtech.savingzapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SavingzUserTest {

    @Test
    void testToString() {
        //Eingabedaten
        String username = "Test";
        String email = "Test";
        String password = "pw";

        //System under Test aufsetzen
        Savingz_User user = new Savingz_User(username,email,password);
        user.setId(10L);

        //erwartetets Ergebnis
        String expected = "Savingz_User{id=10, username='Test', email='Test', password='pw'}";

        //tatächliches Ergebnis
        String actual = user.toString();

        //Vergleich
        assertEquals(expected,actual);
    }

  
}