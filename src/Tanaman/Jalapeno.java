package Tanaman;

import Grid.Tile;

public class Jalapeno extends Plant {
    public Jalapeno (String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Jalapeno", 100, 0, 0, 150, 0, 20);
    }

    public void clearZombieTile(Tile tile)  {
        if (tile.hasZombie()) {
            tile.removeZombie();
            System.out.println("Zombie cleared from tile!");
        } else{
            System.out.println("There are no zombie in the tile.");
        }
    }
}   
