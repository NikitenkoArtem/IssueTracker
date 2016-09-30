package by.itracker.entity;

import java.io.Serializable;

/**
 * Created by Price on 17.09.2016.
 */
public class Resolution implements Serializable {
    private int resolutionId;
    private String resolutionName;

    public int getResolutionId() {
        return resolutionId;
    }

    public void setResolutionId(int resolutionId) {
        this.resolutionId = resolutionId;
    }

    public String getResolutionName() {
        return resolutionName;
    }

    public void setResolutionName(String resolutionName) {
        this.resolutionName = resolutionName;
    }
}
