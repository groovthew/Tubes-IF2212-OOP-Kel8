package Tanaman;

import Main.Character;
import Zombie.Zombie;

public class Plant extends Character{

    // Mendeklarasikan atribut
    private int cost;
    private int range;
    private int cooldown;
    private boolean isAquatic = false;
    private Plant plant;

    // Method
    public Plant(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super(name, health, attack_damage, attack_speed);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    //getter
    public int getCost(){ return cost;}
    public int getRange(){ return range;}
    public int getCooldown(){ return cooldown;}
    public Plant getPlant() {return plant;}

    public void setHealth(Plant plant) {
        this.plant = plant;
    }

    public boolean getIsAquatic() {
        return isAquatic;
    }
    public void attack(Zombie zombie){
        while(zombie.getHealth() != 0 && this.getHealth() != 0){
            int remainingHealth = zombie.getHealth();
            remainingHealth -= this.getAttackDamage();
            zombie.setHealth(remainingHealth);
        }
    }
}
