package tigo.aplanchados.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tigo.aplanchados.dtos.PermissionDTO;
import tigo.aplanchados.dtos.RoleDTO;
import tigo.aplanchados.dtos.UserDTO;
import tigo.aplanchados.mappers.PermissionMapper;
import tigo.aplanchados.mappers.RoleMapper;
import tigo.aplanchados.mappers.UserMapper;
import tigo.aplanchados.model.Role;
import tigo.aplanchados.model.User;
import tigo.aplanchados.services.interfaces.PermissionService;
import tigo.aplanchados.services.interfaces.RoleService;
import tigo.aplanchados.services.interfaces.UserService;

@RestController
@RequestMapping("/manager")
public class ManagerRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(UserMapper.INSTANCE.toDTOs(userService.findAllUsers()));
    }

    @GetMapping("/permissions")
    public ResponseEntity<List<PermissionDTO>> getPermissions() {
        return ResponseEntity.ok(PermissionMapper.INSTANCE.toDTOs(permissionService.findAllPermissions()));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getRoles() {
        return ResponseEntity.ok(RoleMapper.INSTANCE.toDTOs(roleService.findAllRoles()));
    }

    @PostMapping("/add-role")
    public ResponseEntity<?> addRole(@RequestBody RoleDTO roleDTO) {

        String roleName = roleDTO.getName();
        System.out.println(roleName);

        if (roleName == null || roleName.isEmpty()) {
            return ResponseEntity.badRequest().body("Role name is required");
        }
        if (roleService.findRoleByName(roleName).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Role already exists");
        }
        Role role = new Role();
        role.setName(roleName);
        roleService.saveRole(role);

        return ResponseEntity.ok("Role added successfully");

    }

    @PostMapping("/remove-role")
    public ResponseEntity<?> removeRole(@RequestBody Map<String, String> allparams) {
        System.out.println(allparams);
        String replacementRole = allparams.get("replacement-role");
        String roleToDelete = allparams.get("role-to-delete");
        System.out.println(roleToDelete);
        boolean flag = roleService.removeRole(roleToDelete, replacementRole);
        if (flag) {
            return ResponseEntity.ok("Role removed successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid roles id not found");
        }

    }

    @PostMapping("/update-role")
    public ResponseEntity<?> updateRoles(@RequestBody Map<String, String> allParams) {
        
        boolean flag =roleService.updateRoleServices(allParams);
        System.out.println(allParams);
        if(flag){
            return ResponseEntity.ok("Roles updated successfully");
        }else{
            return ResponseEntity.badRequest().body("Invalid roles id not found");
        }
    }

        @PostMapping("/edit-user-role")
        public ResponseEntity<?> editUserRole(@RequestBody UserDTO userDTO){

            User foundUser=userService.findUserById(  userDTO.getId() ).get();
            Role foundRole=roleService.findRoleById(userDTO.getRole()).get();
            if (foundRole == null) {
                return ResponseEntity.badRequest().body("Invalid role id not found");
            }
            foundUser.setRole(foundRole);
            boolean flag=userService.updateUser(foundUser);
            if(flag){
                return ResponseEntity.ok("User role updated successfully");
            }else{
                return ResponseEntity.badRequest().body("Invalid user id not found");
            }

        }
}


