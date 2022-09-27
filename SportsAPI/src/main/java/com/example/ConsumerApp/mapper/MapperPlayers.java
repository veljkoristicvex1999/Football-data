package com.example.ConsumerApp.mapper;

import com.example.ConsumerApp.Dto.AreaDto;
import com.example.ConsumerApp.Dto.PlayerDto;
import com.example.ConsumerApp.model.Area;
import com.example.ConsumerApp.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MapperPlayers {

    private final MapperTeams mapperTeams;
    private final MapperArea mapperArea;

    @Autowired
    public MapperPlayers(MapperTeams mapperTeams, MapperArea mapperArea) {
        this.mapperArea = mapperArea;
        this.mapperTeams = mapperTeams;
    }

    public Player playersDtoToModel(PlayerDto playerDto) {
        Player player = new Player();
        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        Area area = mapperArea.areaDtoToModel(playerDto.getAreaDto());
        player.setArea(area);
        player.setId(playerDto.getId());
        player.setTeams(playerDto.getTeamsDtoList().stream().map(team -> mapperTeams.teamsDtoToModel(team)).collect(Collectors.toList()));
        return player;
    }

    public PlayerDto modelToPlayersDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setFirstName(player.getFirstName());
        playerDto.setLastName(player.getLastName());
        AreaDto areaDto = mapperArea.modelToAreaDto(player.getArea());
        playerDto.setAreaDto(areaDto);
        playerDto.setTeamsDtoList(player.getTeams().stream().map(team -> mapperTeams.modelToTeamsDto(team)).collect(Collectors.toList()));
        return playerDto;
    }
}
