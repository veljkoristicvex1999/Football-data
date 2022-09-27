package com.example.ConsumerApp.cotroller;

import com.example.ConsumerApp.Dto.AreaDto;
import com.example.ConsumerApp.Service.AreaService;
import com.example.ConsumerApp.exception.NotFoundDataExcepiton;
import com.example.ConsumerApp.mapper.MapperArea;
import com.example.ConsumerApp.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/areas")
public class ControllerArea {

    private AreaService areaService;
    private final MapperArea mapperArea;

    @Autowired
    public ControllerArea(AreaService areaService, MapperArea mapperArea) {
        this.areaService = areaService;
        this.mapperArea = mapperArea;
    }

    @GetMapping
    public List<AreaDto> allArea(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<Area> areaList = areaService.getAllAreas(page, size);
        return areaList.stream()
                .map(mapperArea::modelToAreaDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AreaDto findArea(@PathVariable(value = "id") int id) throws NotFoundDataExcepiton {
        return mapperArea.modelToAreaDto(areaService.findArea(id));
    }

    @DeleteMapping("/{id}")
    public void deleteArea(@PathVariable(value = "id") Integer id) {
        areaService.logicalDeletionArea(id);
    }

    @PutMapping("/{id}")
    public AreaDto updateArea(@PathVariable(value = "id") int id, @RequestBody AreaDto areaDto) {
        Area area = areaService.updateArea(id, mapperArea.areaDtoToModel(areaDto));
        return mapperArea.modelToAreaDto(area);
    }


    @GetMapping("/search/{name}")
    public List<AreaDto> searchByName(@PathVariable("name") String name) {
        List<Area> areas = areaService.searchByName(name);
        return areas.stream()
                .map(mapperArea::modelToAreaDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public AreaDto saveArea(@RequestBody AreaDto areaDto) throws Exception {
        Area area = areaService.saveArea(mapperArea.areaDtoToModel(areaDto));
        return mapperArea.modelToAreaDto(area);
    }

//swager moze i samo dependency ali nekad ces imati potrebu i da sam pravis svoj swager
}

