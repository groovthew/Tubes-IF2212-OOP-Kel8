package Map;

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

//     public Tile(boolean hasPlant, List<Zombie> zombielist, Plant placePlant) {
//         this.hasPlant = false;
//         this.zombieslist =  new ArrayList<>();
//         this.placePlant = null;
//     }
    
//     public boolean IsHasPlant() {
//         return hasPlant;
//     }

//     public boolean getHasZombie() {
//         return isZombie;
//     }

//     public void removeZombie(Zombie z) {
//         zombieslist.remove(z);
//     }

//     public void addZombie(Zombie zombie) {
//         zombieslist.add(zombie);
//     }

//     public List<Zombie> getZombiesList() {
//         return zombieslist;
//     }

//     public Plant getPlacedPlant() {
//         return placePlant;
//     }

//     public void Planting(Plant p) {
//         if (!hasPlant) {
//             placePlant = p;
//             hasPlant = true;
//         }
//     }

//     public void Digging() {
//         if (hasPlant) {
//             placePlant = null;
//             hasPlant = false;
//         }
//     }
// }
