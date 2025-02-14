package player;

import java.util.ArrayList;
import java.util.List;
import items.ItemManager;

public class Player implements IDamageable {
    private String name;
    private int health;
    private int experience;
    private List<ItemManager> inventory;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.experience = 0;
        this.inventory = new ArrayList<>();
    }

    public void gainExperience(int xp) {
        experience += xp;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            return;
        }
    }

    public int getHealth() {
        return health;
    }

    public void pickUpItem(ItemManager item) {
        inventory.add(item);
    }

    public List<ItemManager> getInventory() {
        return inventory;
    }
}