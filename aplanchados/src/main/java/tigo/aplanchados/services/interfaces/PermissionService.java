package tigo.aplanchados.services.interfaces;

import tigo.aplanchados.model.Permission;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface PermissionService {

    List<Permission> findAllPermissions();

    Optional<Permission> findPermissionById(Long id);

    Permission savePermission(Permission permission);

    void deletePermission(Long id);

    Optional<Permission> findPermissionByName(String name);
}
