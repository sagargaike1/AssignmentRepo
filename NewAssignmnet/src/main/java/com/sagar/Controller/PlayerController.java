package com.sagar.Controller;

import com.sagar.DAO.PlayerRepository;
import com.sagar.DAO.TeamRepository;
import com.sagar.Model.Player;
import com.sagar.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player createdPlayer = playerRepository.save(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }
}