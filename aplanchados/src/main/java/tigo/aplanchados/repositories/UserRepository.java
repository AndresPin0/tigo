package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tigo.aplanchados.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    
}

