package by.itracker.entity;

import java.io.Serializable;

/**
 * Created by Price on 17.09.2016.
 */
public class Type implements Serializable {
    private int typeId;
    private String typeName;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
