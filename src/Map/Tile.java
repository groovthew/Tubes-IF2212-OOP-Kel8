package Map;

<<<<<<< HEAD
import java.util.List;
import java.util.ArrayList;
import Tanaman.*;
import Zombie.*;
public class Tile {
    private List<Plant> plants = new ArrayList<>();
    private List<Zombie> zombies = new ArrayList<>();
    private boolean isWater;
    private boolean isSpawnArea;
    private boolean hasPlant;
    private boolean isZombie;

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
            System.out.println("Zombie spawned on tile.");
        }
    }
    public boolean getHasZombie() {
        return isZombie;
    }
    public boolean IsHasPlant() {
    return hasPlant;
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
}
// public class Tile {
//     private boolean hasPlant;
//     private List<Zombie> zombieslist;
//     private Plant placePlant;
//     private boolean isZombie;
=======
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Tanaman.*;  // Assuming specific plant classes are under this package
import Zombie.*; // Assuming specific zombie classes are under this package
>>>>>>> 83f8ed203db26ac0fed32833d8c15c3dd30d1fe0

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
            System.out.println("Zombie spawned on tile.");
        }
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

    public int getPlantHealth() {
        if (!plants.isEmpty()) {
            return plants.get(0).getHealth();
        } else {
            return -1;
        }
    }

    public void setPlantHealth(int health) {
        if (!plants.isEmpty()) {
            plants.get(0).setHealth(health);
        }
    }
}