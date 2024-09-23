//package tigo.aplanchados.services.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import tigo.aplanchados.model.User;
//import tigo.aplanchados.repositories.UserRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class UserDetailServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Long id;
//        try {
//            id = Long.parseLong(username);
//        } catch (NumberFormatException e) {
//            throw new UsernameNotFoundException("User not found with id: " + username);
//        }
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + username));
//
//        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
//
//        user.getRole().getRolePermissions().forEach(role ->
//                authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole().getName()))));
//
//        user.getRole().getRolePermissions().stream()
//                .flatMap(role -> role.getPermission().getRolePermissions().stream())
//                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermission().getName())));
//
//
//        return new org.springframework.security.core.userdetails.User(
//                String.valueOf(user.getId()), user.getPassword(), authorityList);
//    }
//}
