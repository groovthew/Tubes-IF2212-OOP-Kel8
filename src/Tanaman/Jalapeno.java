package Tanaman;

// import Map.Tile;

import Map.Tile;

public class Jalapeno extends Plant {
    public Jalapeno (String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Jalapeno", 100, 0, 0, 150, 0, 20, false);
    }

    public void clearZombieRow(Tile[][] tiles, int row) {
        for (Tile tile : tiles[row]) {
            tile.removeAllZombies();
        }
    }
}   
