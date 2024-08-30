package tigo.aplanchados.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tigo.aplanchados.model.Permission;
import tigo.aplanchados.repositories.PermissionRepository;
import tigo.aplanchados.services.interfaces.PermissionService;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> findPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Optional<Permission> findPermissionByName(String name) {
        return permissionRepository.findAll().stream()
                .filter(permission -> name.equals(permission.getName()))
                .findFirst();
    }
}
