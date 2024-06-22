package webtech.savingzapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Savingz_User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    public Savingz_User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Savingz_User() {}

    @Override
    public String toString() {
        return "Savingz_User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}