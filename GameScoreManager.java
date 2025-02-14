/**
 * GameScoreManager is a concrete implementation of the ScoreManager interface.
 * 
 * SOLID Applied:
 * - Single Responsibility Principle (SRP): This class is solely responsible for managing and updating the score.
 * - Dependency Inversion Principle (DIP): It implements ScoreManager, allowing higher-level classes to work with this abstraction.
 */
public class GameScoreManager implements ScoreManager {
    private int score = 0;
    
    @Override
    public void updateScore(int points) {
        score += points;
        System.out.println("Score updated: " + score);
    }
    
    @Override
    public int getScore() {
        return score;
    }
}
