package tigo.aplanchados.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tigo.aplanchados.model.User;
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.services.interfaces.UserService;
@Controller
@RequestMapping("/manager")
public class ManagerController {
   @Autowired
   private UserService userService;
   @GetMapping
   public String managerPage(Model model) {
      model.addAttribute("users", userService.findAllUsers());
      return "manager";
   }

   
   @RequestMapping(value="/edit-user-role",method=RequestMethod.POST)

   public String editUserRole(@ModelAttribute User user, Model model){
      System.out.println(user.getName());
     
      System.out.println(user.getName());
      System.out.println(user.getRole().getName());
      return "redirect:/manager";
   }



}
