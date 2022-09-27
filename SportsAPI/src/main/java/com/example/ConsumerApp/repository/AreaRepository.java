package com.example.ConsumerApp.repository;

import com.example.ConsumerApp.model.Area;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends PagingAndSortingRepository<Area, Integer> {

    Area findByAreaId(int areaId);

    public List<Area> findAreaByDeletedFlag(Boolean deletedFlag, Pageable pageable);

    @Query("select area from Area area where area.areaId =:id and area.deletedFlag = false")
    public Area findArea(@Param("id") Integer id);

    List<Area> findByNameIsContaining(@Param("name") String name);

    @Query("select area from Area area where area.deletedFlag = false and (area.name =:name or area.countryCode =:countryCode)")
    public List<Area> findAvailableArea(@Param("countryCode") String countryCode, @Param("name") String name);

}
