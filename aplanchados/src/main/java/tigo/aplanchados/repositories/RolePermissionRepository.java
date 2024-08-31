package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.RolePermission;
import tigo.aplanchados.model.RolePermissionPK;
//bien
public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionPK> {

}
