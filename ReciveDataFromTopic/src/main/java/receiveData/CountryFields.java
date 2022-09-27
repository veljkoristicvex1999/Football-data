package receiveData;


import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificRecord;

public class CountryFields implements SpecificRecord {
    private int id;
    private String name;
    private String countryCode;
    private String flag;
    private Integer parentAreaId;
    private String parentArea;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getParentAreaId() {
        return parentAreaId;
    }

    public void setParentAreaId(Integer parentAreaId) {
        this.parentAreaId = parentAreaId;
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea;
    }

    public CountryFields() {

    }

    public CountryFields(int id, String name, String countryCode, String flag, Integer parentAreaId, String parentArea) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.flag = flag;
        this.parentAreaId = parentAreaId;
        this.parentArea = parentArea;
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

