package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tigo.aplanchados.model.Permission;
import tigo.aplanchados.model.Role;
import tigo.aplanchados.model.RolePermission;
import tigo.aplanchados.model.RolePermissionPK;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionPK> {
    RolePermission findByRoleAndPermission(Role role, Permission permission);

}
