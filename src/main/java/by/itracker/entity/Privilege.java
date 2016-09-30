package by.itracker.entity;

import java.io.Serializable;

/**
 * Created by Price on 18.09.2016.
 */
public class Privilege implements Serializable {
    private int privilegeId;
    private String privilegeName;
    private Role roleId;

    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }
}
