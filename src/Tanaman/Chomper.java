package Tanaman;

import Map.Tile;
import Zombie.*;

public class Chomper extends Plant {
    private boolean isEating; // Untuk menandai apakah Chomper sedang makan atau tidak
    private int eatingCooldown; // Waktu cooldown setelah Chomper makan

    public Chomper(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Chomper", 1000, 1000, 0, 150, 0, 10,false);
        this.isEating = false;
        this.eatingCooldown = 0;
    }

    public void instantKillZombie(Tile[][] tiles, int row, int col) {
        if (!isEating && col > 0 && !tiles[row][col - 1].getZombies().isEmpty()) {
            Zombie zombie = tiles[row][col - 1].getZombies().get(0);
            zombie.setHealth(0); // Instantly kill the zombie
            tiles[row][col - 1].getZombies().remove(zombie); // Remove the zombie from the tile
            System.out.println("Chomper instantly killed a zombie on tile [" + row + "][" + (col - 1) + "]");
            isEating = true;
            eatingCooldown = 10; // Set cooldown after eating
        }
    }

    public void eatingCoolDown() {
        if (isEating) {
            eatingCooldown--;
            if (eatingCooldown <= 0) {
                isEating = false;
            }
        }
    }
}
