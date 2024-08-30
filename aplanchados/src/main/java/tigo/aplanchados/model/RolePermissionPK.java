package tigo.aplanchados.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.io.Serializable;
import jakarta.persistence.JoinColumn;

@Embeddable
@Data
public class RolePermissionPK implements Serializable {

    @Column(name = "role_id", insertable = false, updatable = false)
    private Long roleId;

    @Column(name = "permission_id", insertable = false, updatable = false)
    private Long permissionId;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private Permission permission;
}
