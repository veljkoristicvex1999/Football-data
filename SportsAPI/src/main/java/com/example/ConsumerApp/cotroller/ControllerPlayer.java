package com.example.ConsumerApp.cotroller;

import com.example.ConsumerApp.Dto.PlayerDto;
import com.example.ConsumerApp.Service.PlayerService;
import com.example.ConsumerApp.mapper.MapperPlayers;
import com.example.ConsumerApp.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/players")
@Validated
public class ControllerPlayer {

    private final MapperPlayers mapperPlayers;
    private final PlayerService playerService;

    @Autowired
    public ControllerPlayer(MapperPlayers mapperPlayers, PlayerService playerService) {
        this.mapperPlayers = mapperPlayers;
        this.playerService = playerService;
    }

    @PostMapping
    public PlayerDto insertTeam(@Valid @RequestBody PlayerDto playerDto) throws Exception {
        Player player = playerService.savePlayer(mapperPlayers.playersDtoToModel(playerDto));
        return mapperPlayers.modelToPlayersDto(player);
    }

    @GetMapping("/search/{name}")
    public List<PlayerDto> searchPlayers(@PathVariable("name") String name) {
        List<Player> players = playerService.searchPlayers(name);
        return players.stream()
                .map(mapperPlayers::modelToPlayersDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<PlayerDto> all() {
        List<Player> players = playerService.getAllPlayers();
        return players.stream().map(mapperPlayers::modelToPlayersDto).collect(Collectors.toList());
    }

    @PutMapping
    public int update(@Valid @RequestBody PlayerDto playerDto) {
        playerService.update(mapperPlayers.playersDtoToModel(playerDto));
        return 1;
    }

    @GetMapping("/homePlayers")
    public List<PlayerDto> homePlayers() {
        List<Player> players = playerService.homePlayers();
        return players.stream()
                .map(mapperPlayers::modelToPlayersDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/searchPlayers")
    public List<PlayerDto> searchPlayers(@Valid @RequestBody List<Integer> list) {
        List<Player> players = playerService.searchPlayersById(list);
        return players.stream()
                .map(mapperPlayers::modelToPlayersDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/team/all-players/{id}")
    public List<PlayerDto> getAllPlayersFromTeam(@PathVariable("id") Integer id) {
        System.out.println(id);
        List<Player> players = playerService.getPlayersFromOneTeam(id);
        players.get(0);
        return players.stream()
                .map(mapperPlayers::modelToPlayersDto)
                .collect(Collectors.toList());
    }
}
