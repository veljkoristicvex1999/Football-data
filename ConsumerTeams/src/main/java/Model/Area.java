package Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Area {
    @Id
    private int areaId;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }
}
