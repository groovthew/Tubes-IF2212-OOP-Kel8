package Zombie;

import Main.Character;
import Tanaman.Peashooter;
import Tanaman.Plant;
import Map.Tile;

public abstract class Zombie extends Character {
    private int walking_speed;
    private boolean isAquatic;
    private boolean is_slowed;
    private Tile tile;
    

    public Zombie(String name, int health, int attack_damage, int attack_speed){
        super(name, health, attack_damage, attack_speed);
        this.walking_speed = 3;
        this.isAquatic = false;
        this.is_slowed = false;
    }

    public Zombie(String name, int health, int attack_damage, int attack_speed, boolean isAquatic){
        super(name, health, attack_damage, attack_speed);
        this.isAquatic = isAquatic;
        this.walking_speed = 3;
        this.is_slowed = false;
    }

    public Zombie(String name, int health, int attack_damage, int attack_speed, int walking_speed){
        super(name, health, attack_damage, attack_speed);
        this.walking_speed = walking_speed;
        this.isAquatic = false;
        this.is_slowed = false;
    }

    //getter
    public int getWalkingSpeed(){ return walking_speed;}
    public boolean getIsAquatic(){ return isAquatic;}
    public boolean getIsSlow(){ return is_slowed;}

    //setter
    public void setWalkingSpeed(int walking_speed){ this.walking_speed = walking_speed;}

    // abstract method
    public void attack(){
        if (tile.getPlants() != null && tile.getZombies().contains(this)) {
            while(tile.getPlant().getHealth() != 0 && this.getHealth() != 0){
                int remainingHealth = tile.getPlants().getHealth();
                remainingHealth -= this.getAttackDamage();
                tile.getPlants().setHealth(remainingHealth);
            }
        }
    }
}
