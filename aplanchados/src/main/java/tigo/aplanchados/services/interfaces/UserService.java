package tigo.aplanchados.services.interfaces;

import tigo.aplanchados.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface UserService {

    List<User> findAllUsers();

    Optional<User> findUserById(Long id);

    User createUser(User user);

    void deleteUser(Long id);

    Optional<User> findUserByUsername(String username);

    boolean updateUser(User user);

    void save(User user);

}
