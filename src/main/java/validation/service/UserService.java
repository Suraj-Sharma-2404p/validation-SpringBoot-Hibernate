package validation.service;

import validation.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    List<User> findAll();
    Optional<User> findById(int id);
}
