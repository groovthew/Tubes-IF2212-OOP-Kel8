package Tanaman;

import Map.Tile;

public class Squash extends Plant {
    public Squash(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Squash", 100, 5000, 0, 50, 1, 20, false);
    }

    public void attackZombie(Tile[][] tiles, int i, int j) {
        if (j > 0 && !tiles[i][j - 1].getZombies().isEmpty()) {
            tiles[i][j - 1].getZombies().clear();
            tiles[i][j].getPlants().remove(this);
            return;
        }
        if (j < tiles[i].length - 1 && !tiles[i][j + 1].getZombies().isEmpty()) {
            tiles[i][j + 1].getZombies().clear();
            tiles[i][j].getPlants().remove(this);
            return;
        }
        if (!tiles[i][j].getZombies().isEmpty()) {
            tiles[i][j].getZombies().clear();
            tiles[i][j].getPlants().remove(this);
        }
    }
}
