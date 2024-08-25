package tigo.aplanchados.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserRolePK implements Serializable{
    
    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;
    @Column(name = "role_id", insertable = false, updatable = false)
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user) {
        this.userId = user;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer role) {
        this.roleId = role;
    }
}
