package tigo.aplanchados.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="role_permission")
public class RolePermission implements Serializable {
    @EmbeddedId
    private RolePermissionPK rolePermissionPK;
}
