package com.example.ConsumerApp.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TeamDTO {

    private int id;
    @NotEmpty
    @NotNull
    private String clubColors;

    @NotEmpty
    @NotNull
    private String address;

    @NotNull(message = "can't be null")
    @NotEmpty(message = "Can't be empty")
    @Pattern(regexp = "[A-Za-z]+", message = "Invalid input, your input can't contain number")
    private String name;
    private AreaDto areaDto;

    public AreaDto getAreaDto() {
        return areaDto;
    }

    public void setAreaDto(AreaDto areaDto) {
        this.areaDto = areaDto;
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

}
