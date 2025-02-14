SOLID-Refactored Adventure Game 
Introduction 
This document provides an in-depth explanation of the refactored adventure game, highlighting the application of SOLID principles. The game involves a player encountering different enemies, engaging in combat, gaining experience, and progressing through levels while maintaining a structured and maintainable codebase.
	
SOLID Principles Applied
1. Single Responsibility Principle (SRP)
Each class has a single, well-defined responsibility:
•	Player manages health, experience, and inventory.
•	public class Player implements IDamageable {
    private String name;
    private int health;
    private int experience;
    private List<ItemManager> inventory;
•	CombatManager handles combat logic.
•	public class CombatManager {
    private ScoreManager scoreManager;
•	Enemy spawns and manages enemies.
•	public class Enemy implements IEnemy  {
    private String type;
    private int points;
    private int health;
    private int attackPower;
•	LevelManager handles level progression.
•	public class LevelManager {
    private int currentLevel;
    private Random random;
•	ScoreManager tracks the player’s score.
•	public class ScoreManager {

    public int score;

2. Open/Closed Principle (OCP)
•	The Enemy class is abstract, allowing new enemy types to be added without modifying existing logic.
•	The IEnemy interface ensures future enemy variations can be introduced without breaking the system.
•	public class Enemy implements IEnemy  {
    private String type;
    private int points;
    private int health;
    private int attackPower;

    public Enemy(String type, int points, int health, int attackPower) {
        this.type = type;
        this.points = points;
        this.health = health;
        this.attackPower = attackPower;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public String getType() {
        return type;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getPoints() {
        return points;
    }
}


3. Liskov Substitution Principle (LSP)
•	Zombie and Vampire extend Enemy and can be used interchangeably in the combat system without modifying any other logic
public class Zombie extends Enemy {
    public Zombie() {
        super("Zombie", 50,40,30);
    }
}

public class Vampire extends Enemy {
    public Vampire() {
        super("Vampire", 70,50,70);
    }
}

4. Interface Segregation Principle (ISP)
•	IDamageable interface allows different entities (Player, Enemies) to be damaged without forcing unrelated behaviors.
•	package player;

public interface IDamageable {

    void takeDamage(int damage);
    int getHealth();
}
•	IEnemy interface defines only enemy-specific behaviors, ensuring separation of concerns.
•	package enemies;

import player.IDamageable;

public interface IEnemy extends IDamageable {
    int getAttackPower();
    int getPoints();
}

5. Dependency Inversion Principle (DIP)
•	CombatManager depends on IEnemy rather than concrete enemy implementations, making it flexible for future extensions.
•	package combat;

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


Class Descriptions
1. Player
•	Represents the player in the game.
•	Implements IDamageable.
•	Attributes: 
o	name: The player’s name.
o	health: Current health points.
o	experience: Tracks gained XP.
o	inventory: Holds collected items.
•	Methods: 
o	gainExperience(int xp): Increases experience.
o	pickUpItem(Item item): Adds an item to inventory.
o	takeDamage(int damage): Reduces health when attacked.
2. CombatManager
•	Manages combat between the player and enemies.
•	Uses IEnemy for enemy interactions.
•	Methods: 
o	engage(Player player, IEnemy enemy): Executes the fight loop.
3. Enemy & Enemy Types
•	Abstract Enemy class with common attributes: 
o	health
o	attackPower
o	points
•	Implements IEnemy.
•	Zombie and Vampire extend Enemy, inheriting its behavior.
4. LevelManager
•	Manages level progression.
•	Spawns enemies dynamically based on current level.
•	Methods: 
o	spawnEnemy(): Generates an enemy.
o	nextLevel(): Increments the level.
5. ScoreManager
•	Tracks and updates player’s score.
•	Methods: 
o	addScore(int points): Increments score.
o	getScore(): Retrieves current score.

 


Game Flow
1.	Game Starts
o	MainGame initializes Player, CombatManager, LevelManager, and ScoreManager.
2.	Enemy Encounter
o	LevelManager spawns an enemy.
o	CombatManager handles combat.
3.	Combat Execution
o	Player and enemy exchange attacks.
o	If the enemy is defeated, experience and points are awarded.
o	If the player is defeated, the game ends.
4.	Level Progression
o	If the player survives, LevelManager advances the game to the next level.
o	A new enemy is spawned, and the loop continues.
5.	Game Over
o	When the player’s health reaches zero, the final score is displayed.

Conclusion
This refactored adventure game follows SOLID principles, improving maintainability, scalability, and code organization. The separation of concerns allows easy modifications and extensions, ensuring long-term flexibility.

