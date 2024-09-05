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



    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private Permission permission;


}
