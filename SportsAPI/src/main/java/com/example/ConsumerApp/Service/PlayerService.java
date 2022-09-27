package com.example.ConsumerApp.Service;

import com.example.ConsumerApp.model.Area;
import com.example.ConsumerApp.model.Player;
import com.example.ConsumerApp.repository.AreaRepository;
import com.example.ConsumerApp.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final AreaRepository areaRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, AreaRepository areaRepository) {
        this.playerRepository = playerRepository;
        this.areaRepository = areaRepository;
    }

    public Player savePlayer(Player newPlayer) throws Exception {
        newPlayer.setDeletedFlag(false);
        Area area = areaRepository.findArea(newPlayer.getArea().getAreaId());
        //  System.out.println(area.getAreaId());
        if (area == null) {
            return playerRepository.save(newPlayer);
        }
        throw new Exception("Nema area");
    }

    public List<Player> searchPlayers(String searchTerm) {
        List<Player> players = playerRepository.searchPlayers(searchTerm);
        return players;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public int update(Player newPlayer) {
        playerRepository.updatePlayer(newPlayer.getFirstName(), newPlayer.getLastName(), newPlayer.getId());
        playerRepository.save(newPlayer);
        return 1;
    }

    public List<Player> homePlayers() {
        return playerRepository.homePlayers();
    }

    public List<Player> searchPlayersById(List<Integer> list) {
        return playerRepository.searchPlayersByAreaId(list);
    }

    public List<Player> getPlayersFromOneTeam(Integer teamId) {
        return playerRepository.getPlayerfromTeam(teamId);
    }
}
