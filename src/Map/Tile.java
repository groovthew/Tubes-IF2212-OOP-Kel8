package Map;

import java.util.List;
import java.util.ArrayList;
import Tanaman.*;
import Zombie.*;

public class Tile {
    private boolean hasPlant;
    private List<Zombie> zombieslist;
    private Plant placePlant;

    public Tile(boolean hasPlant, List<Zombie> zombielist, Plant placePlant) {
        this.placePlant = null;
        this.hasPlant = false;
        this.zombieslist =  new ArrayList<>();
    }
    
    public boolean IsHasPlant() {
        return hasPlant;
    }

    public List<Zombie> getZombiesList() {
        return zombieslist;
    }

    public Plant getPlacedPlant() {
        return placePlant;
    }

    public void Planting(Plant p) {
        if (!hasPlant) {
            placePlant = p;
            hasPlant = true;
        }
    }

    public void Digging() {
        if (hasPlant) {
            placePlant = null;
            hasPlant = false;
        }
    }

}