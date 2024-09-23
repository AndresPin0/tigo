package tigo.aplanchados.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
@Data
public class User implements Serializable {
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
}
