package tigo.aplanchados.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "app_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable, UserDetails {
  @Id
  private Long id;
  private String name;
  private String lastName;
  private String password;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Income> incomes;

  @OneToMany(mappedBy = "user")
  private List<Expense> expenses;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    this.getRole().getRolePermissions()
        .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermission().getName())));
    return authorityList;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public String getUsername() {
    return id.toString();
  }

  public boolean isAccountNonLocked() {
    return true;
  }

  public boolean isCredentialsNonExpired() {
    return true;
  }

  public boolean isEnabled() {
    return true;
  }



}
