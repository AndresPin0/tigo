package tigo.aplanchados.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tigo.aplanchados.model.User;
import tigo.aplanchados.model.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByRole(Role role);

    //Optional<User> findById(Long id);
    
}
