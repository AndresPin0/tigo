package tigo.aplanchados.model;

import jakarta.persistence.*;
import jakarta.validation.OverridesAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="app_user")
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
    @JoinColumn(name="role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Income> incomes;

    @OneToMany(mappedBy = "user")
    private List<Expense> expenses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return List.of(new SimpleGrantedAuthority("AUTORIDAD"));
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
