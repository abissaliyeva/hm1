/**
 * Enemy represents an individual enemy entity.
 *
 * SOLID Applied:
 * - Single Responsibility Principle (SRP): This class is responsible only for holding enemy data (type and points).
 */
public class Enemy {
    private String type;
    private int points;
    
    public Enemy(String type, int points) {
        this.type = type;
        this.points = points;
    }
    
    public String getType() {
        return type;
    }
    
    public int getPoints() {
        return points;
    }
}
