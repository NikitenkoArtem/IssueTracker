package by.epam.entity;

import java.io.Serializable;

/**
 * Created by Price on 17.09.2016.
 */
public class Role implements Serializable {
    private int roleId;
    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
