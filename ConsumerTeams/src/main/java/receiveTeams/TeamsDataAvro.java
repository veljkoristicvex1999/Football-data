package receiveTeams;

import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificRecord;

public class TeamsDataAvro {
    private int id;
    private int areaId;
    private String name;
    private String address;
    private String clubColors;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public TeamsDataAvro(int id, int areaId, String name, String address, String clubColors) {
        this.id = id;
        this.areaId = areaId;
        this.name = name;
        this.address = address;
        this.clubColors = clubColors;
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



    //@Override
    public void put(int i, Object o) {

    }

//   // @Override
//    public Object get(int i) {
//        return null;
//    }
//
//   // @Override
//    public Schema getSchema() {
//        return null;
//    }
}

