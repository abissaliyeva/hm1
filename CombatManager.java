package combat;

import enemies.IEnemy;
import player.Player;
import score.ScoreManager;

public class CombatManager {
    private ScoreManager scoreManager;

    public CombatManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    public void engage(Player player, IEnemy enemy) {
        String enemyType = enemy.getClass().getSimpleName();
        System.out.println("A " + enemyType + " appears!");

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            int playerAttackDamage = 10;
            enemy.takeDamage(playerAttackDamage);
            System.out.println("Player attacks " + enemyType + " for " + playerAttackDamage + " damage.");

            if (enemy.getHealth() <= 0) {
                System.out.println(enemyType + " defeated!");
                int points = enemy.getAttackPower() * 10;
                player.gainExperience(enemy.getPoints());
                scoreManager.addScore(points);
                System.out.println("Player earned " + points + " points!");
                return;
            }

            int enemyAttackDamage = enemy.getAttackPower();
            player.takeDamage(enemyAttackDamage);
            System.out.println(enemyType + " attacks player for " + enemyAttackDamage + " damage.");
        }

        if (player.getHealth() <= 0) {
            System.out.println("Player has been defeated!");
        }
    }
}
