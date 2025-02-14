package main;

import player.Player;
import combat.CombatManager;
import enemies.IEnemy;
import level.LevelManager;
import score.ScoreManager;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Hero");
        ScoreManager scoreManager = new ScoreManager();
        LevelManager levelManager = new LevelManager();
        CombatManager combatManager = new CombatManager(scoreManager);

        while (player.getHealth() > 0) {
            IEnemy enemy = levelManager.spawnEnemy();
            combatManager.engage(player, enemy);

            if (player.getHealth() > 0) {
                levelManager.nextLevel();
            }
        }

        System.out.println("Game over! Final score: " + scoreManager.getScore());
    }
}
