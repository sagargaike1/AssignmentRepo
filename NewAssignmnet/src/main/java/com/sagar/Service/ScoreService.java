package com.sagar.Service;

import com.sagar.DAO.PlayerRepository;
import com.sagar.DAO.ScoreRepository;
import com.sagar.DAO.TeamRepository;
import com.sagar.Model.Player;
import com.sagar.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    public void processRound(Map<Team, List<String>> teamPlayers, List<String> scoreOrder, List<List<Integer>> scores) {
        Map<Team, Integer> teamScores = new HashMap<>();
        Map<Player, Integer> individualScores = new HashMap<>();
        Map<Team, Integer> bonusPoints = new HashMap<>();

        // Initialize scores
        for (Team team : teamPlayers.keySet()) {
            teamScores.put(team, 0);
            bonusPoints.put(team, 0);
            for (String playerName : teamPlayers.get(team)) {
                Player player = playerRepository.findByName(playerName);
                individualScores.put(player, 0);
            }
        }

        for (int i = 0; i < scoreOrder.size(); i++) {
            String circle = scoreOrder.get(i);
            List<Integer> roundScores = scores.get(i);

            for (int j = 0; j < roundScores.size(); j++) {
                String playerName = teamPlayers.get(j).get(i);
                Player player = playerRepository.findByName(playerName);
                Team team = player.getTeam();

                int score = roundScores.get(j);
                individualScores.put(player, individualScores.get(player) + score);
                teamScores.put(team, teamScores.get(team) + score);
            }

            for (Team team : teamPlayers.keySet()) {
                List<String> players = teamPlayers.get(team);
                if (individualScores.get(playerRepository.findByName(players.get(0))).equals(individualScores.get(playerRepository.findByName(players.get(1))))) {
                    bonusPoints.put(team, bonusPoints.get(team) + 2);
                }
            }
        }


        for (Team team : teamScores.keySet()) {
            team.setScore(team.getScore() + teamScores.get(team) + bonusPoints.get(team));
            teamRepository.save(team);
        }

        // Save individual scores
        for (Player player : individualScores.keySet()) {
            player.setScore(player.getScore() + individualScores.get(player));
            playerRepository.save(player);
        }
    }
}
