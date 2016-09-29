package by.epam.entity;

import java.io.Serializable;

/**
 * Created by Price on 18.09.2016.
 */
public class Privilege implements Serializable {
    private int privilegeId;
    private String privilegeName;
    private int role;

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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
