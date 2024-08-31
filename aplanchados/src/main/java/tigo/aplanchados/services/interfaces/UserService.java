package tigo.aplanchados.services.interfaces;

import tigo.aplanchados.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers();

    Optional<User> findUserById(Long id);

    User saveUser(User user);

    void deleteUser(Long id);

    Optional<User> findUserByUsername(String username);

}
