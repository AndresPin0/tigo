package tigo.aplanchados.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
@Entity
@Data
@Table(name="role_permission")
public class RolePermission implements Serializable {
    @EmbeddedId
    private RolePermissionPK rolePermissionPK;

    @OneToMany

    private Role role;

    private Permission permission;




}
