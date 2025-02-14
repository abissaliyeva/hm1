
package level;

import enemies.IEnemy;
import enemies.Zombie;
import enemies.Vampire;
import java.util.Random;

public class LevelManager {
    private int currentLevel;
    private Random random;

    public LevelManager() {
        this.currentLevel = 1;
        this.random = new Random();
    }

    public IEnemy spawnEnemy() {
        if (currentLevel == 1) {
            return new Zombie();
        } else if (currentLevel == 2) {
            return new Vampire();
        } else {
            return random.nextBoolean() ? new Zombie() : new Vampire();
        }
    }

    public void nextLevel() {
        currentLevel++;
        System.out.println("Advancing to level " + currentLevel);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}
