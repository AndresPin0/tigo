package tigo.aplanchados.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tigo.aplanchados.model.Permission;
import tigo.aplanchados.model.Role;
import tigo.aplanchados.model.RolePermission;
import tigo.aplanchados.model.RolePermissionPK;
import tigo.aplanchados.model.User;
import tigo.aplanchados.repositories.PermissionRepository;
import tigo.aplanchados.repositories.RolePermissionRepository;
import tigo.aplanchados.repositories.RoleRepository;
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.services.interfaces.RoleService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;



    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role saveRole(Role role) {
        if(roleRepository.existsByName(role.getName())){
            return null;
        }   

        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findAll().stream()
                .filter(role -> name.equals(role.getName()))
                .findFirst();
    }



    @Override
    public boolean deleteRole(Role role){
        if(!roleRepository.existsById(role.getId()) || role.getName().equals("EMPLOYEE")){
            return false;
        }
        userRepository.findAllByRole(role).forEach(user -> {
            user.setRole(roleRepository.findByName("EMPLOYEE"));
            userRepository.save(user);
        });

        roleRepository.deleteById( roleRepository.findByName(role.getName()).getId() );
        return true;
    }


    @Override
    public boolean addPermissionToRole(Role role, Permission permission){
        Role foundRole=roleRepository.findByName(role.getName());
        Permission foundPermission=permissionRepository.findByName(permission.getName());

        if (foundRole==null ){
            return false;
        }
        if (foundPermission ==null){
            return false;
        }

        if (rolePermissionRepository.findByRoleAndPermission(foundRole,foundPermission)!=null){
            return false;
        }

        RolePermissionPK rolePermissionPK = new RolePermissionPK();
        rolePermissionPK.setRole(foundRole);
        rolePermissionPK.setPermission(foundPermission);

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRolePermissionPK(rolePermissionPK);

        rolePermissionRepository.save(rolePermission);
        return true;
    }


    @Override
    public boolean removePermissionToRole(Role role, Permission permission){
        Role foundRole=roleRepository.findByName(role.getName());
        Permission foundPermission=permissionRepository.findByName(permission.getName());

        if (foundRole==null ){
            return false;
        }
        if (foundPermission ==null){
            return false;
        }
        if (rolePermissionRepository.findByRoleAndPermission(foundRole,foundPermission)==null){
            return false;
        }
        rolePermissionRepository.deleteById( rolePermissionRepository.findByRoleAndPermission(foundRole,foundPermission).getRolePermissionPK());
        return true;

    }

    @Override
    public boolean changeUserRole(User user, Role role){
        if(userRepository.findById(user.getId()).isEmpty()){
            return false;
        }
        if(roleRepository.findByName( role.getName() )==null){
            return false;
        }
        if(user.getRole().getName().equals(role.getName())){
            return false;
        }

        user.setRole( roleRepository.findByName(role.getName()) );
        userRepository.save(user);
        return true;
    }


    @Override
    public boolean deleteUserRole(User givenUser){
        if(userRepository.findById(givenUser.getId()).isEmpty()){
            return false;
        }
        User user= userRepository.findById(givenUser.getId()).get();
        user.setRole( roleRepository.findByName("EMPLOYEE") );
        userRepository.save(user);
        return true;
    }

    @Override
    public Map<String,List<String>> getRolesPermissions( ){
        Map<String,List<String>> map= new HashMap<>();

        List<Role> roles= roleRepository.findAll();

        for(Role role: roles){
            map.put(role.getName(),role.getRolePermissions().stream().map(rolePemission->rolePemission.getPermission().getName()).toList());
        }



      return map;
       

    }


    @Override
    public boolean updateRoleServices(Map<String,String> roleServices){

        String foundRoleName=null;
        Role role=null;

        foundRoleName=roleServices.get("roleName");
        
        if(foundRoleName==null){
            return false;
        }
        if(!roleRepository.existsByName(foundRoleName)){
            return false;
        }

        Set<String> roleServicesSet=new HashSet<>();
        
        for( Map.Entry<String, String> entry: roleServices.entrySet()){
            if(entry.getKey().equals("roleName")){
                continue;
            }
            roleServicesSet.add(entry.getKey());
        }
        role=new Role();
        role.setName(foundRoleName);
        for(Permission permission: permissionRepository.findAll()){

            String permissionName=permission.getName();
            if(roleServicesSet.contains(permissionName)){
                if(addPermissionToRole(role, permission)){
                }
            }else{
                if(  removePermissionToRole(role, permissionRepository.findByName(permissionName)) ){
                    
                }
            }


        }


        return true;
    }



    






}
