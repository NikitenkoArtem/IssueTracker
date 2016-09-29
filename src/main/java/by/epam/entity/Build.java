package by.epam.entity;

import java.io.Serializable;

/**
 * Created by Price on 17.09.2016.
 */
public class Build implements Serializable {
    private int buildId;
    private String build;
    private int project;

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }
}
