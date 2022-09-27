package com.example.ConsumerApp.mapper;

import com.example.ConsumerApp.Dto.AreaDto;
import com.example.ConsumerApp.Dto.TeamDTO;
import com.example.ConsumerApp.model.Area;
import com.example.ConsumerApp.model.Team;
import com.example.ConsumerApp.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperTeams {

    private final MapperArea mapperArea;
    private final AreaRepository areaRepository;
    
    @Autowired
    public MapperTeams(MapperArea mapperArea, AreaRepository areaRepository) {
        this.mapperArea = mapperArea;
        this.areaRepository = areaRepository;
    }

    public Team teamsDtoToModel(TeamDTO teamsDto) {
        Team team = new Team();
        team.setName(teamsDto.getName());
        team.setAddress(teamsDto.getAddress());
        team.setId(teamsDto.getId());
        team.setClubColors(teamsDto.getClubColors());
        Area area = mapperArea.areaDtoToModel(teamsDto.getAreaDto());
        team.setArea(area);
        return team;
    }

    public TeamDTO modelToTeamsDto(Team team) {
        TeamDTO teamsDto = new TeamDTO();
        teamsDto.setId(team.getId());
        teamsDto.setAddress(team.getAddress());
        teamsDto.setName(team.getName());
        teamsDto.setClubColors(team.getClubColors());
        AreaDto areaDto = mapperArea.modelToAreaDto(team.getArea());
        teamsDto.setAreaDto(areaDto);
        return teamsDto;
    }
}
