package tigo.aplanchados.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable

@Data

public class RolePermissionPK implements Serializable {
    @Column(name = "role_id", insertable = false, updatable = false)
    private long roleId;
    @Column(name = "permission_id", insertable = false, updatable = false)
    private long permissionId;
}
