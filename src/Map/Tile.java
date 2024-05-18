package Map;

import java.util.ArrayList;
import java.util.List;
import Tanaman.*;  // Assuming specific plant classes are under this package
import Zombie.*; // Assuming specific zombie classes are under this package

public class Tile {
    private List<Plant> plants = new ArrayList<>();
    private List<Zombie> zombies = new ArrayList<>();
    private boolean isWater;
    private boolean isSpawnArea;

    public Tile(boolean isWater, boolean isSpawnArea) {
        this.isWater = isWater;
        this.isSpawnArea = isSpawnArea;
    }

    public void addPlant(Plant plant) {
        if ((isWater && plant.getIsAquatic()) || !isWater && !isSpawnArea) {
            plants.add(plant);
            System.out.println("Added " + plant.getName() + " to " + (isWater ? "water" : "land") + " tile.");
        } else {
            System.out.println("Cannot place " + plant.getName() + " on this type of tile."); // Nanti dibuat exception
        }
    }

    public void addZombie(Zombie zombie) {
        if (isSpawnArea) {
            zombies.add(zombie);
            // System.out.println("Zombie spawned on tile.");
        }
    }

    public void removeZombie(Zombie zombie) {
        zombies.remove(zombie);
        // System.out.println("Zombie removed from tile.");
    }

    public boolean isWater() {
        return isWater;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }
    public List<Plant> getPlants(){
        return plants;
    }

    public void removeAllZombies() {
        zombies.clear();
        // System.out.println("Semua Zombie telah mati.");
    }
}