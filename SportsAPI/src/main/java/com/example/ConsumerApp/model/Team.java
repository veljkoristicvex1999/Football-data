package com.example.ConsumerApp.model;

import javax.persistence.*;

@Entity(name = "Team")
@Table
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String clubColors;
    private String address;

    //@Column(unique = true)
    private String name;

    private boolean deletedFlag;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "area")
    Area area;

    public void setDeletedFlag(boolean deleted_flag) {
        this.deletedFlag = deletedFlag;
    }

    public boolean isDeletedFlag() {
        return deletedFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClubColors() {
        return clubColors;
    }

    public void setClubColors(String clubColors) {
        this.clubColors = clubColors;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
