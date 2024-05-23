package Zombie;

import Map.Tile;
import Tanaman.Plant;

public class PoleVaultingZombie extends Zombie {
    private boolean hasJumped = false;

    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1);
    }

    public boolean hasJumped() {
        return hasJumped;
    }

    // Method untuk melakukan lompatan
    public void jumpTile(Tile[][] tiles, int i, int j) {
        if (hasJumped) {
            return;
        }
        // Pastikan bahwa tile berikutnya berada dalam batas dan terdapat tanaman
        if (j >= 2 && !tiles[i][j - 1].getPlants().isEmpty()) {
                Plant plant = tiles[i][j - 1].getPlants().get(0);
                tiles[i][j - 1].getPlants().remove(plant);
                tiles[i][j].getZombies().remove(this);
                tiles[i][j - 2].getZombies().add(this);
                hasJumped = true;
                System.out.println(getName() + " jumped from tile [" + i + "][" + j + "] to tile [" + i + "][" + (j - 2) + "]");
        } 
    }
    
}
