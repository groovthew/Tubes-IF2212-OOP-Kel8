package Tanaman;

import Grid.Tile;
import Zombie.Zombie;

public class SnowPea extends Plant {
    public SnowPea(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Snow Pea", 100, 25, 4, 175, -1, 10);
    }

    public void shootSnowPea(Tile tile) {
        if (tile.hasZombie()) {
            Zombie zombie = tile.getZombie();
            System.out.println("Snow Pea shoots a frozen pea at the zombie!");
            zombie.takeDamage(getAttackDamage()); // gambaran
            zombie.freeze(); // ini harus ada method ini di zombie(?)
        } else {
            System.out.println("No zombie on this tile to shoot at.");
        }
    }
}
