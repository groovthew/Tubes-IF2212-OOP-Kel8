package Tanaman;


public class Lilypad extends Plant {
    Plant plant;
    Plant plantOnTop;
    public Lilypad(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Lilypad", 100, 0, 0, 25, 0, 10, true);
    }

    public Plant getPlantOnTop() {
        return plantOnTop;
    }

    public boolean hasPlantOnTop() {
        return plantOnTop != null;
    }

    public void setPlantOnTop(Plant plantOnTop) {
        this.plantOnTop = plantOnTop;
        if (plantOnTop != null) {
            this.setHealth(plantOnTop.getHealth());
            this.setName(plantOnTop.getName());
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public int totalHealth() {
        int totalHealth = 0;
        if (plantOnTop != null) {
            totalHealth += plantOnTop.getHealth();
        }
        totalHealth += this.getHealth();
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
}


