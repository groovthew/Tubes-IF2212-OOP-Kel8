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
        if (hasLilypad() && lilypad.getPlantOnTop() == null && !plant.isLilypad()) {
            lilypad.setPlantOnTop(plant);
            System.out.println("Added " + plant.getName() + " on top of Lilypad on " + (isWater ? "water" : "land") + " tile.");
            updateTileName();
        } else if ((isWater && plant.getIsAquatic()) || (!isWater && !isSpawnArea) || (isWater && plant.isLilypad())) {
            plants.add(plant);
            if (plant.isLilypad()) {
                lilypad = (Lilypad) plant;
            }
            System.out.println("Added " + plant.getName() + " to " + (isWater ? "water" : "land") + " tile.");
            updateTileName();
        } else {
            System.out.println("Cannot place " + plant.getName() + " on this type of tile.");
        }
    }

    public void placeLilypad(Lilypad lilypad) {
        if (isWater && this.lilypad == null) {
            this.lilypad = lilypad;
            plants.add(lilypad);
            System.out.println("Lilypad placed on water tile.");
            updateTileName();
        } else {
            System.out.println("Cannot place Lilypad on this tile.");
        }
    }

    public boolean hasLilypad() {
        return lilypad != null;
    }

    public void updateTileName() {
        StringBuilder tileName = new StringBuilder();
        int totalHealth = 0;

        for (Plant plant : plants) {
            if (plant instanceof Lilypad && ((Lilypad) plant).getPlantOnTop() != null) {
                tileName.append("[").append(((Lilypad) plant).getPlantOnTop().getName()).append(": ").append(((Lilypad) plant).getPlantOnTop().getHealth()).append("]");
                totalHealth += ((Lilypad) plant).getPlantOnTop().getHealth();
            } else {
                tileName.append("[").append(plant.getName()).append(": ").append(plant.getHealth()).append("]");
                totalHealth += plant.getHealth();
            }
        }

        // If there are no plants or Lilypad, show an empty tile
        if (tileName.length() == 0) {
            tileName.append("[         ]");
        }

        System.out.println(tileName.toString() + " Total Health: " + totalHealth);
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
    }

    public Lilypad getLilypad() {
        return lilypad;
    }
}
