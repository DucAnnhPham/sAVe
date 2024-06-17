package webtech.savingzapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import webtech.savingzapp.model.Savingz_User;
import webtech.savingzapp.persistance.SavingzUserRepository;

@Service
public class SavingzUserService {

    @Autowired
    private SavingzUserRepository savingzUserRepository;
    @Autowired
    private PasswordHashingService passwordHashingService;

    public ResponseEntity<Savingz_User> register(Savingz_User newSavingzUser) {
        Savingz_User existingSavingzUser = savingzUserRepository.findByUsername(newSavingzUser.getUsername());
        if (existingSavingzUser != null) {
            throw new RuntimeException("Username already exists");
        }
        existingSavingzUser = savingzUserRepository.findByEmail(newSavingzUser.getEmail());
        if (existingSavingzUser != null) {
            throw new RuntimeException("Email already exists");
        }
        newSavingzUser.setPassword(passwordHashingService.hashPassword(newSavingzUser.getPassword()));
        Savingz_User savedUser = this.savingzUserRepository.save(newSavingzUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    public Savingz_User login(String username, String password) {
        Savingz_User existingSavingzUser = savingzUserRepository.findByUsername(username);
        if (existingSavingzUser == null || !passwordHashingService.checkPassword(password, existingSavingzUser.getPassword())) {
            return null;
        }
        return existingSavingzUser;
    }
}
