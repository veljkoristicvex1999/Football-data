package com.example.ConsumerApp.Service;

import com.example.ConsumerApp.exception.NotFoundDataExcepiton;
import com.example.ConsumerApp.model.Area;
import com.example.ConsumerApp.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class AreaService {

    private AreaRepository areaRepository;

    @Autowired
    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<Area> getAllAreas(int page, int size) {
        List<Area> areas = areaRepository.findAreaByDeletedFlag(false, PageRequest.of(page, size));
        return areas;
    }

    public Area saveArea(Area newArea) throws Exception {
        if (areaRepository.findAvailableArea(newArea.getCountryCode(), newArea.getName()).isEmpty()) {
            return areaRepository.save(newArea);
        }
        throw new Exception("County code or name area allready exist in db");
    }

    public Area findArea(Integer id) throws NotFoundDataExcepiton {
        Area area = areaRepository.findArea(id);
        if (area != null)
            return area;
        else {
            throw new NotFoundDataExcepiton("Can't found  data");
        }
    }

    public void logicalDeletionArea(Integer id) {
        Area area = areaRepository.findArea(id);
        area.setDeletedFlag(true);
        areaRepository.save(area);
    }

    public Area updateArea(Integer id, @Valid Area newArea) {
        Area area = areaRepository.findByAreaId(id);
        if (area != null) {
            area.setCountryCode(newArea.getCountryCode());
            area.setParentArea(newArea.getParentArea());
            area.setFlag(newArea.getFlag());
            area.setParentAreaId(newArea.getParentAreaId());
            area.setName(newArea.getName());
            return areaRepository.save(area);
        }
        return null;
    }

    public List<Area> searchByName(String name) {
        return areaRepository.findByNameIsContaining(name);
    }
}
