package com.example.ConsumerApp.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AreaDto {

    private int areaId;
    @NotNull(message = "name can't be null")
    @NotEmpty(message = "empty name area")
    @Pattern(regexp = "[A-Za-z]+", message = "Invalid input, your input can't contain number")
    private String nameArea;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[A-Za-z]+", message = "Invalid input, your input can't contain number")
    private String countryCode;
    private String flag;
    private int parentAreaId;
    private String parentArea;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
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


}
