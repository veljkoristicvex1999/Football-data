package com.example.ConsumerApp.repository;

import com.example.ConsumerApp.model.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface TeamsRepository extends JpaRepository<Team, Integer> {

    Team findTeamById(Integer id);


    @Transactional
    @Query(value = "insert into Team(address, clubColors, deletedFlag, name, area) values (:team.address, :team.clubColors, :team.deletedFlag, :team.name, :team.area", nativeQuery = true)
    int saveTeam(@PathVariable("team") Team team);

    List<Team> findTeamByDeletedFlag(Boolean bo, Pageable pageable);

    @Query("select team from Team team where team.deletedFlag = false and team.name=:name")
    List<Team> findTeams(@Param("name") String name);

    @Query("select team from Team team where team.id=:id and team.deletedFlag=false")
    Team findAvailableTeam(@Param("id") Integer id);

    List<Team> findByNameIsContaining(@Param("name") String name);

}
