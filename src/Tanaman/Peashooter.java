package Tanaman;

import java.util.Timer;


public class Peashooter extends Plant {
    private boolean isCooldown;
    private int shootingCooldown; 
    private Timer timer;

    public Peashooter (String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Peashooter", 100, 25, 4, 100, -1, 10,false);
        this.isCooldown = false;
        this.shootingCooldown = 4;
        this.timer = new Timer();
    }

    public int getHealth(){
        return super.getHealth();
    }

    public boolean isCooldown() {
        return isCooldown;
    }

    public void setCooldown(boolean isCooldown) {
        this.isCooldown = isCooldown;
    }

    public int getShootingCooldown() {
        return shootingCooldown;
    }
}
