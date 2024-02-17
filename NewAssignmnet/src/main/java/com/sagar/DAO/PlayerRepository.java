package com.sagar.DAO;

import com.sagar.Model.Player;
import com.sagar.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByName(String playerName);
}
