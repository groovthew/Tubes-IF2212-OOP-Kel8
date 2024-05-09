package Tanaman;

import Main.Character;

public class Plant extends Character{

    // Mendeklarasikan atribut
    private int cost;
    private int range;
    private int cooldown;

    // Method
    public Plant(String name, int health, int attack_damage, int attack_speed, int x, int y, int cost, int range, int cooldown) {
        super(name, health, attack_damage, attack_speed);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    //getter
    public int getCost(){ return cost;}
    public int getRange(){ return range;}
    public int getCooldown(){ return cooldown;}
}
