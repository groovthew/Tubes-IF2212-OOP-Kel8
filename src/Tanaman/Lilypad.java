package Tanaman;

import Zombie.*;

public class Lilypad extends Plant {
    Plant plant;
    Plant plantOnTop;
    public Lilypad(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Lilypad", 100, 0, 0, 25, 0, 10);
    }

    public Plant getPlantOnTop() {
        return plantOnTop;
    }

    public void setPlantOnTop(Plant plantOnTop) {
        this.plantOnTop = plantOnTop;
    }

    public int totalHealth() {
        int totalHealth = this.getHealth();
        if (plantOnTop != null) {
            totalHealth += plantOnTop.getHealth();
        }
        return totalHealth;
    }

    public void decreaseHealth(int attackDamage) {
        if (plantOnTop != null) {
            int plantHealth = plantOnTop.getHealth() - attackDamage;
            if (plantHealth <= 0) {
                // Plant on top is dead, remove it
                plantOnTop = null;
            } else {
                plantOnTop.setHealth(plantHealth);
                return; 
            }
        }
        int lilypadHealth = this.getHealth() - attackDamage;
        if (lilypadHealth <= 0) {
            this.setHealth(0);
        } else {
            this.setHealth(lilypadHealth);
        }
    }

    public static void main (String[] args) {
        // Lilypad lilypad = new Lilypad("Lilypad", 100, 0, 0, 25, 0, 10);
        // System.out.println("Cost of plant:  " + lilypad.getCost());
        // System.out.println("Name of plant: " + lilypad.getName());
        // System.out.println("iS AQUATIC?" + lilypad.getIsAquatic());
        Peashooter plant1 = new Peashooter("Peashooter", 50, 20, 1, 50, 1, 5);
        Lilypad lilypad = new Lilypad("Lilypad", 100, 0, 0, 25, 0, 10);
        lilypad.setPlantOnTop(plant1);

        NormalZombie zombie1 = new NormalZombie();

        System.out.println("Total Health: " + lilypad.totalHealth());
        lilypad.decreaseHealth(zombie1.getAttackDamage());

        System.out.println("Total Health: " + lilypad.totalHealth());
    }
}


