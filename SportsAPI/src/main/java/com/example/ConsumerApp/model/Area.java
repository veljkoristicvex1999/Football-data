package com.example.ConsumerApp.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Area")
@Table
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;

    private boolean deletedFlag;

    //@Column(unique = true)
    private String name;

    //@Column(unique = true)
    private String countryCode;

    private String flag;
    private int parentAreaId;
    private String parentArea;
    //ovde bilo one to many
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<Team> teams;

    public boolean isDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(boolean deleted_flag) {
        this.deletedFlag = deletedFlag;
    }

    public int getAreaId() {
        return areaId;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
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

    public int getParentAreaId() {
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

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }


}
