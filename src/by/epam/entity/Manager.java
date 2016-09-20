package by.epam.entity;

import java.io.Serializable;

/**
 * Created by Price on 17.09.2016.
 */
public class Manager implements Serializable {
    private int managerId;
    private int user;

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
