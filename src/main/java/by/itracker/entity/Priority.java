package by.itracker.entity;

import java.io.Serializable;

/**
 * Created by Price on 17.09.2016.
 */
public class Priority implements Serializable {
    private int priorityId;
    private String priorityName;

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }
}
