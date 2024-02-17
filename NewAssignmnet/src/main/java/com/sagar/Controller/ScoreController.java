package com.sagar.Controller;

import com.sagar.DAO.ScoreRepository;
import com.sagar.DAO.TeamRepository;
import com.sagar.Model.Score;
import com.sagar.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Score> createScore(@RequestBody Score score) {
        Score createdScore = scoreRepository.save(score);
        return new ResponseEntity<>(createdScore, HttpStatus.CREATED);
    }
}
