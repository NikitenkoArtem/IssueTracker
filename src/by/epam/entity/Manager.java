package by.epam.entity;

import java.io.Serializable;

/**
 * Created by Price on 17.09.2016.
 */
public class Manager implements Serializable {
    private int managerId;
    private String email;

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
