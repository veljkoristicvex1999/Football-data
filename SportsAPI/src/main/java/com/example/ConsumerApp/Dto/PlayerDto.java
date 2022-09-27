package com.example.ConsumerApp.Dto;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class PlayerDto {

    private int id;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[A-Za-z]+", message = "Invalid input, your input can't contain number")
    private String firstName;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[A-Za-z]+", message = "Invalid input, your input can't contain number")
    private String lastName;

    private AreaDto areaDto;

    @UniqueElements
    private List<TeamDTO> teamsDtoList;

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AreaDto getAreaDto() {
        return areaDto;
    }

    public void setAreaDto(AreaDto areaDto) {
        this.areaDto = areaDto;
    }

    public List<TeamDTO> getTeamsDtoList() {
        return teamsDtoList;
    }

    public void setTeamsDtoList(List<TeamDTO> teamsDtoList) {
        this.teamsDtoList = teamsDtoList;
    }
}
