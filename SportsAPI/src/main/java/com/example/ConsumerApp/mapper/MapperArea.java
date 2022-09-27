package com.example.ConsumerApp.mapper;

import com.example.ConsumerApp.Dto.AreaDto;
import com.example.ConsumerApp.model.Area;
import org.springframework.stereotype.Component;

@Component
public class MapperArea {

    public AreaDto modelToAreaDto(Area area) {
        AreaDto areaDto = new AreaDto();
        areaDto.setAreaId(area.getAreaId());
        areaDto.setNameArea(area.getName());
        areaDto.setParentArea(area.getParentArea());
        areaDto.setCountryCode(area.getCountryCode());
        areaDto.setFlag(area.getFlag());
        areaDto.setParentAreaId(area.getParentAreaId());
        return areaDto;
    }

    public Area areaDtoToModel(AreaDto areaDto) {
        Area area = new Area();
        area.setName(areaDto.getNameArea());
        area.setParentArea(areaDto.getParentArea());
        area.setParentAreaId(areaDto.getParentAreaId());
        area.setCountryCode(areaDto.getCountryCode());
        area.setFlag(areaDto.getFlag());
        return area;
    }
}
