package com.example.ConsumerApp.cotroller;

import com.example.ConsumerApp.Dto.TeamDTO;
import com.example.ConsumerApp.Service.TeamService;
import com.example.ConsumerApp.mapper.MapperTeams;
import com.example.ConsumerApp.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(value = "/teams")
public class ControllerTeams {

    private final TeamService service;
    private final MapperTeams mapperTeams;

    @Autowired
    public ControllerTeams(TeamService service, MapperTeams mapperTeams) {
        this.service = service;
        this.mapperTeams = mapperTeams;
    }

    @GetMapping
    public List<TeamDTO> allTeams(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<Team> teamList = service.getAllTeams(page, size);
        return teamList.stream()
                .map(mapperTeams::modelToTeamsDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TeamDTO findTeam(@PathVariable("id") int id) {
        Team team = service.findTeam(id);
        return mapperTeams.modelToTeamsDto(team);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.logicalDeletion(id);
    }

    @PutMapping("/{id}")
    public TeamDTO update(@PathVariable(value = "id") int id, @RequestBody TeamDTO teamsDto) {
        Team team = service.updateTeam(id, mapperTeams.teamsDtoToModel(teamsDto));
        return teamsDto;
    }

    @PostMapping
    public int saveTeam(@Valid @RequestBody TeamDTO teamsDto) {
        //  Team team = service.saveTeam(mapperTeams.teamsDtoToModel(teamsDto));
        return service.saveTeam(mapperTeams.teamsDtoToModel(teamsDto));
        
    }

    @GetMapping("/search/{name}")
    public List<TeamDTO> search(@PathVariable(value = "name") String name) {
        List<Team> teams = service.searchTeams(name);
        return teams.stream()
                .map(mapperTeams::modelToTeamsDto)
                .collect(Collectors.toList());
    }
}
