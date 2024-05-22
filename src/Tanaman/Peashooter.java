package Tanaman;

import Map.Tile;
import Zombie.Zombie;

public class Peashooter extends Plant {
    public Peashooter (String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Peashooter", 100, 25, 4, 100, -1, 10,false);
    }

    public int getHealth(){
        return super.getHealth();
    }
}
