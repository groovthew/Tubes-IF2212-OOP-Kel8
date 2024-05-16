package Zombie;

import Map.Tile;
import Zombie.PoleVaultingZombie;
import Zombie.Zombie;

public class PoleVaultingZombie extends Zombie {
    private boolean hasJumped = false;

    public PoleVaultingZombie(){
        super("Pole Vaulting Zombie", 175, 100, 1);
    }

    public void jumpTile(Tile[][] tiles, int row, int col) {
        if (col > 0 && col < tiles[row].length - 1 && !hasJumped) {
            Tile currentTile = tiles[row][col];
            Tile nextTile = tiles[row][col - 1];

            if (!nextTile.getPlants().isEmpty()) {
                // Perform the jump
                Tile targetTile = tiles[row][col - 2 < 0 ? 0 : col - 2];
                targetTile.addZombie(this);
                currentTile.removeZombie(this);
                hasJumped = true;
                System.out.println(this.getName() + " jumped over a plant and landed on tile [" + row + "][" + (col - 2 < 0 ? 0 : col - 2) + "]");
            }
        }
    }
}
