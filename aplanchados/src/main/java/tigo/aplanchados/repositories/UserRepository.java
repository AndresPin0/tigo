package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
