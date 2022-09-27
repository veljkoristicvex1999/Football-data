package com.example.ConsumerApp.Service;

import com.example.ConsumerApp.model.Team;
import com.example.ConsumerApp.repository.AreaRepository;
import com.example.ConsumerApp.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class TeamService {

    private AreaRepository areaRepository;
    private TeamsRepository teamsRepository;

    @Autowired
    public TeamService(AreaRepository areaRepository, TeamsRepository teamsRepository) {
        this.areaRepository = areaRepository;
        this.teamsRepository = teamsRepository;
    }

    public List<Team> getAllTeams(int offset, int size) {
        return teamsRepository.findTeamByDeletedFlag(false, PageRequest.of(offset, size));
    }

    public Team findTeam(Integer id) {
        return teamsRepository.findAvailableTeam(id);
    }

    // provera za postojecu areu
    public int saveTeam(Team newTeam) {
        return teamsRepository.saveTeam(newTeam);
    }

    public void logicalDeletion(Integer id) {
        Team team = teamsRepository.findAvailableTeam(id);
        team.setDeletedFlag(true);
        teamsRepository.save(team);
    }

    public Team updateTeam(Integer id, @Valid Team newTeam) {
        Team team = teamsRepository.findTeamById(id);
        team.setArea(newTeam.getArea());
        team.setAddress(newTeam.getAddress());
        team.setClubColors(newTeam.getClubColors());
        team.setName(newTeam.getName());
        return teamsRepository.save(team);
    }


    public List<Team> searchTeams(String name) {
        return teamsRepository.findByNameIsContaining(name);
    }
}
