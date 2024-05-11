package Tanaman;

import Map.Tile;
import Zombie.Zombie;

public class Jalapeno extends Plant {
    public Jalapeno (String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Jalapeno", 100, 0, 0, 150, 0, 20);
    }

    public void clearZombieTile(Tile tile, Zombie zombie)  {
        if (tile.getHasZombie()) {
            tile.removeZombie(zombie);
            System.out.println("Zombie cleared from tile!");
        } else{
            System.out.println("There are no zombie in the tile."); // Exception
        }
    }

    // public static void main(String[] args) {
    //     // Membuat objek Tile
    //     Tile tile = new Tile(false, new ArrayList<>(), null);

    //     // Menambahkan zombie ke daftar zombie pada tile
    //     Zombie zombie = new Zombie();
    //     tile.getZombiesList().add(zombie);

    //     // Memeriksa apakah ada zombie di tile
    //     System.out.println("Is there a zombie in the tile? " + tile.getHasZombie());

    //     // Menghapus zombie dari tile
    //     tile.removeZombie();

    //     // Memeriksa kembali apakah masih ada zombie di tile setelah dihapus
    //     System.out.println("Is there still a zombie in the tile? " + tile.getHasZombie());
    // }
}   
