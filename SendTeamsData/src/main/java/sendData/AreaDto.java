package sendData;

public class AreaDto{
private int id;
private String name;
private String code;
private String flag;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public AreaDto() {
    }

    public AreaDto(int id, String name, String code, String flag) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.flag = flag;
    }
}
