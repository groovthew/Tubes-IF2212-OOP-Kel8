package Tanaman;

// import Map.Tile;

import Map.Tile;

public class Jalapeno extends Plant {
    public Jalapeno (String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Jalapeno", 100, 0, 0, 150, 0, 20, false);
    }

    public void attackRow(Tile[][] tiles, int i, int j) {
        for (int col = 0; col < tiles[i].length; col++) {
            if (!tiles[i][col].getZombies().isEmpty()) {
                tiles[i][col].getZombies().clear();
            }
        }
        tiles[i][j].getPlants().remove(this);
        }
}   
