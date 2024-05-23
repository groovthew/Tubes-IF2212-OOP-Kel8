package Zombie;

import Map.Tile;

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
        // Jika sudah melompat, kembalikan method
        if (hasJumped) {
            return;
        }

        // Pastikan bahwa tile berikutnya berada dalam batas dan terdapat tanaman
        if (j > 0 && !tiles[i][j - 1].getPlants().isEmpty()) {
            // Pastikan bahwa tile dua langkah ke kiri berada dalam batas
            if (j - 2 >= 0) {
                // Pindahkan zombie ke tile dua langkah ke kiri
                tiles[i][j - 2].addZombie(this);
                tiles[i][j].removeZombie(this);
                hasJumped = true;
                System.out.println(getName() + " jumped from tile [" + i + "][" + j + "] to tile [" + i + "][" + (j - 2) + "]");
            } else {
                // Jika tidak bisa melompat dua langkah, pindahkan ke satu langkah ke kiri
                tiles[i][j - 1].addZombie(this);
                tiles[i][j].removeZombie(this);
                hasJumped = true;
                System.out.println(getName() + " jumped from tile [" + i + "][" + j + "] to tile [" + i + "][" + (j - 1) + "]");
            }
        }
    }
}
