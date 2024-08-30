package tigo.aplanchados.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable{
    @Id
    private Long id;
    private String name;
    private String lastName;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;




    
}
