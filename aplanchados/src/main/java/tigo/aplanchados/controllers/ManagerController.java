package tigo.aplanchados.controllers;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tigo.aplanchados.model.Permission;
import tigo.aplanchados.model.User;
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.services.interfaces.PermissionService;
import tigo.aplanchados.services.interfaces.RoleService;
import tigo.aplanchados.services.interfaces.UserService;
@Controller
@RequestMapping("/manager")
public class ManagerController {
   @Autowired
   private UserService userService;
   @Autowired
   private RoleService roleService;
   @Autowired
   private PermissionService permissionService;

   @GetMapping
   public String managerPage(Model model) {
      model.addAttribute("users", userService.findAllUsers());
      model.addAttribute("roles", roleService.findAllRoles());
      return "manager";
   }

   
   @RequestMapping(value="/edit-user-role",method=RequestMethod.POST)

   public String editUserRole(@ModelAttribute User user, Model model){
      return "redirect:/manager";
   }

   @GetMapping("/roles")
   public String manageRoles(Model model){
      model.addAttribute("permissions", permissionService.findAllPermissions());
      model.addAttribute("roles", roleService.findAllRoles());

      Map<String,List<String>> map=roleService.getRolesPermissions();
      Set<String> set=new HashSet<>();
      for(Map.Entry<String,List<String>> entry: map.entrySet()){
         String role= entry.getKey();
         for (String permission : entry.getValue()) {
            set.add(role+"-"+permission);
         }


      }
      model.addAttribute("rolePermissionSet", set);

      
      

     /* 
      Permission permission1=new Permission();
      Permission permission2=new Permission();
      Permission permission3=new Permission();
      Permission permission4=new Permission();
      permission1.setName("Add expense");
      permission2.setName("Add income");
      permission3.setName("Generate report");
      permission4.setName("Manage system");
      permissionService.savePermission(permission1);
      permissionService.savePermission(permission2);
      
      permissionService.savePermission(permission3);
      permissionService.savePermission(permission4);

*/
      return "roles";
   }

@PostMapping("/update-roles")
public String updateFoos(@RequestParam Map<String,String> allParams) {
   
   allParams.get("roleName"); 
   roleService.updateRoleServices(allParams);

   return "redirect:/manager/roles";

}



}
