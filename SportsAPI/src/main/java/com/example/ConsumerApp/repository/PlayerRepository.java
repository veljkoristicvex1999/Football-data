package com.example.ConsumerApp.repository;

import com.example.ConsumerApp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findPlayerById(int id);

    @Query(value = "insert into PLAYER(firstName,lastName,deletedFlag,area)" +
            " values" +
            " (:player.firstName,:player.lastName,:player.deletedFlag, :player.area)", nativeQuery = true)
    Player savePlayer(@Param("player") Player player);

    @Query("select player from Player player  where lower(player.firstName) like lower(concat('%', :searchterm,'%')) or lower(player.lastName) like lower(concat('%', :searchterm,'%'))")
    public List<Player> searchPlayers(@Param("searchterm") String searchTerm);

    //MODIFING QUERY MORA VOid IILI INT
    @Modifying
    @Transactional
    @Query("update Player set firstName =:firstName, lastName =:lastName where id =:id")
    int updatePlayer(String firstName, String lastName, int id);

    @Query(value = "select * from  Player p JOIN  PLAYER_TEAMS pt on p.ID = pt.PLAYER_ID JOIN TEAM t on  pt.teams_id = t.id where p.area = t.area", nativeQuery = true)
    public List<Player> homePlayers();
    //getovanje playera koji su igrali za svoje domace timove

    @Query(value = "select distinct p.id, p.first_name,p.last_name from  Player p JOIN  PLAYER_TEAMS pt on p.ID = pt.PLAYER_ID JOIN TEAM t on  pt.teams_id = t.id where t.area in (:list) ", nativeQuery = true)
    public List<Player> searchPlayersByAreaId(@Param("list") List<Integer> areaId);
    //endpoint koji prima listu areaId-jeva i getuje playere koji su igrali u tim area-ma

    @Query(value = "select p.first_name, p.last_name from Player p JOIN PLAYER_TEAMS pt on p.id = pt.player_id where pt.teams_id = :id", nativeQuery = true)
    public List<Player> getPlayerfromTeam(@Param("id") int id);

}


