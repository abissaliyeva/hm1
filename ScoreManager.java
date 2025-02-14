/**
 * ScoreManager interface demonstrates the Dependency Inversion Principle (DIP)
 * by defining an abstraction for score management. High-level modules (e.g., Player)
 * depend on this abstraction rather than a concrete implementation.
 */
public interface ScoreManager {
    void updateScore(int points);
    int getScore();
}
