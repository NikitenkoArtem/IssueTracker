package by.itracker.entity;

import java.io.Serializable;

/**
 * Created by Price on 17.09.2016.
 */
public class Build implements Serializable {
    private Integer buildId;
    private String build;
    private Project projectId;

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }
}
