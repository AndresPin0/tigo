package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.Permission;
//listo
public interface PermissionRepository extends JpaRepository<Permission, Long> {
   
}
