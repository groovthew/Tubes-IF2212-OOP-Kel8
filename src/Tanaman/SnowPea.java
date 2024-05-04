package Tanaman;

import Grid.Tile;

public class SnowPea extends Plant {
    public SnowPea(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Snow Pea", 100, 25, 4, 175, -1, 10);
    }

    public void shootSnowPea(Tile tile) {
        if (tile.hasZombie()) {
            
        }
    }
}
