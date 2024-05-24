package Tanaman;

import Main.Character;
import Zombie.Zombie;
import Map.*;

public class Plant extends Character{

    // Mendeklarasikan atribut
    private int cost;
    private int range;
    private int cooldown;
    private boolean isAquatic = false;
  
    // Method
    public Plant(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown, boolean isAquatic) {
        super(name, health, attack_damage, attack_speed);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.isAquatic = isAquatic;
    }

    //getter
    public int getCost(){ return cost;}
    public int getRange(){ return range;}
    public int getCooldown(){ return cooldown;}

    public void setPlantHealth(int health) {
        setHealth(health);
    }

    public boolean getIsAquatic() {
        return isAquatic;
    }

    public boolean isLilypad() {
        return this instanceof Lilypad;
    }

    public int getTotalHealth(Plant plantOnTop) {
        int totalHealth = this.getHealth();
        if (plantOnTop != null) {
            totalHealth += plantOnTop.getHealth();
        }
        return totalHealth;
    }
    
}
