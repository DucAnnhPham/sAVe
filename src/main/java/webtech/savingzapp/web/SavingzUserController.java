package webtech.savingzapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webtech.savingzapp.model.Savingz_User;
import webtech.savingzapp.service.SavingzUserService;

@RestController
@RequestMapping("/auth")
public class SavingzUserController {

    private final SavingzUserService savingzUserService;

    @Autowired
    public SavingzUserController(SavingzUserService savingzUserService) {
        this.savingzUserService = savingzUserService;
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody Savingz_User newSavingzUser) {
        try {
            savingzUserService.register(newSavingzUser);
            return ResponseEntity.ok("Registration successful");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path ="/login")
    public ResponseEntity<?> login(@RequestBody Savingz_User loginUser) {
        Savingz_User user = savingzUserService.login(loginUser.getUsername(), loginUser.getPassword());
        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}