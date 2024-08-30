package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
