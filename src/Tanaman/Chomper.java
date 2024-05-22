package Tanaman;

import Map.Tile;
import Zombie.*;
import java.util.Timer;
import java.util.TimerTask;

public class Chomper extends Plant {
    private boolean isEating; // Untuk menandai apakah Chomper sedang makan atau tidak
    private int eatingCooldown; // Waktu cooldown setelah Chomper makan
    private Timer timer;

    public Chomper(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Chomper", 200, 1000, 0, 150, 0, 10, false);
        this.isEating = false;
        this.eatingCooldown = 15;
        this.timer = new Timer();
    }

    public void instantKillZombie(Tile[][] tiles, int i, int j) {
        if (isEating) {
            return; 
        }
        if (j < tiles[i].length - 1 && !tiles[i][j + 1].getZombies().isEmpty()) {
            tiles[i][j + 1].getZombies().clear();
            isEating = true; 

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isEating = false;
                }
            }, eatingCooldown * 1000);
            System.out.println("Chomper attacked zombies on the right tile [" + i + "][" + (j + 1) + "] and is now on cooldown.");
        }
    }
}
