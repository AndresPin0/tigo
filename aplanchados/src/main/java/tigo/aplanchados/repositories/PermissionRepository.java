package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
   
}
