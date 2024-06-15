package webtech.savingzapp.persistance;

import org.springframework.data.repository.CrudRepository;
import webtech.savingzapp.model.Savingz_User;

public interface SavingzUserRepository extends CrudRepository<Savingz_User, Long> {
    Savingz_User findByUsername(String username);
    Savingz_User findByEmail(String email);
}