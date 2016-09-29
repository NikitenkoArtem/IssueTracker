package by.epam.entity;

import java.io.Serializable;

/**
 * Created by Price on 17.09.2016.
 */
public class Project implements Serializable {
    private int projectId;
    private String projectName;
    private String description;
    private int manager;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }
}
