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
        this.eatingCooldown = 20;
        this.timer = new Timer();
    }

    public boolean isEating() {
        return isEating;
    }

    public void setEating(boolean isEating) {
        this.isEating = isEating;
    }

    public int getEatingCooldown() {
        return eatingCooldown;
    }
}
