package Map;

import java.util.ArrayList;
import java.util.List;
import Tanaman.*;  
import Zombie.*;

public class Tile {
    private List<Plant> plants = new ArrayList<>();
    private List<Zombie> zombies = new ArrayList<>();
    private boolean isWater;
    private boolean isSpawnArea;
    private Lilypad lilypad;

    public Tile(boolean isWater, boolean isSpawnArea) {
        this.isWater = isWater;
        this.isSpawnArea = isSpawnArea;
        this.lilypad = null;
    }

    public void addPlant(Plant plant) {
        if (lilypad != null && lilypad.getPlantOnTop() == null) {
            lilypad.setPlantOnTop(plant);
            System.out.println("Added " + plant.getName() + " on top of Lilypad on " + (isWater ? "water" : "land") + " tile.");
        } else if ((isWater && plant.getIsAquatic()) || (!isWater && !isSpawnArea)) {
            plants.add(plant);
            System.out.println("Added " + plant.getName() + " to " + (isWater ? "water" : "land") + " tile.");
        } else {
            System.out.println("Cannot place " + plant.getName() + " on this type of tile."); 
        }
    }

    public void placeLilypad(Lilypad lilypad) {
        if (isWater && this.lilypad == null) {
            this.lilypad = lilypad;
            plants.add(lilypad); 
            System.out.println("Lilypad placed on water tile.");
        } else {
            System.out.println("Cannot place Lilypad on this tile.");
        }
    }

    public void addZombie(Zombie zombie) {
        if (isSpawnArea) {
            zombies.add(zombie);
        }
    }

    public void removeZombie(Zombie zombie) {
        zombies.remove(zombie);
    }

    public boolean isWater() {
        return isWater;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void removeAllZombies() {
        zombies.clear();
        // System.out.println("All zombies have been removed.");
    }
    
    public Lilypad getLilypad() {
        return lilypad;
    }
}
