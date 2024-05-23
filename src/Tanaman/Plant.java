package Tanaman;

import java.util.Timer;
import java.util.TimerTask;
import Main.Character;

public class Plant extends Character {
    private int cost;
    private int range;
    private int cooldown;
    private boolean isAquatic;
    private boolean isOnCooldown;
    private Timer cooldownTimer;

    public Plant(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown, boolean isAquatic) {
        super(name, health, attack_damage, attack_speed);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.isAquatic = isAquatic;
        this.isOnCooldown = false;
        this.cooldownTimer = new Timer();
    }

    public int getCost() {
        return cost;
    }

    public int getRange() {
        return range;
    }

    public int getCooldown() {
        return cooldown;
    }

    public boolean getIsAquatic() {
        return isAquatic;
    }

    public boolean isOnCooldown() {
        return isOnCooldown;
    }

    public void startCooldown() {
        isOnCooldown = true;
        cooldownTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                isOnCooldown = false;
            }
        }, cooldown * 1000);
    }

    public boolean isLilypad() {
        return this instanceof Lilypad;
    }
}
