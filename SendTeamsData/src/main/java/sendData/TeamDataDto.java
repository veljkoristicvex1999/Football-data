package sendData;

import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificRecord;

public class TeamDataDto implements SpecificRecord{
    private int id;
    private String name;
    private String address;
    private String clubColors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TeamDataDto(int id, int areaId, String name, String address, String clubColors) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.clubColors = clubColors;
    }

    public TeamDataDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClubColors() {
        return clubColors;
    }

    public void setClubColors(String clubColors) {
        this.clubColors = clubColors;
    }

    @Override
    public void put(int i, Object o) {

    }

    @Override
    public Object get(int i) {
        return null;
    }

    @Override
    public Schema getSchema() {
        return null;
    }
    
}
