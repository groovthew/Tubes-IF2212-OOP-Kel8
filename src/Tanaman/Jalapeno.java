package Tanaman;

// import Map.Tile;
import Zombie.*;
import java.util.List;
import java.util.ArrayList;
import Map.Tile;

public class Jalapeno extends Plant {
    public Jalapeno (String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Jalapeno", 100, 0, 0, 150, 0, 20);
    }

    public void clearZombieTile(List<Tile> row) {
        for (Tile tile : row) {
            tile.removeAllZombies();
        }
    }

    // public static void main(String[] args) {
    //     // Create a row of tiles
    //     List<Tile> row = new ArrayList<>();
    //     for (int i = 0; i < 5; i++) {
    //         row.add(new Tile(false, true));
    //     }

    //     // Add zombies to the tiles
    //     for (Tile tile : row) {
    //         tile.addZombie(new NormalZombie("Normal Zombie"));
    //     }

    //     // Display zombies before clearing
    //     System.out.println("Zombies before Jalapeno clears the row:");
    //     for (int i = 0; i < row.size(); i++) {
    //         System.out.println("Tile " + (i + 1) + " has " + row.get(i).getZombies().size() + " zombies.");
    //     }

    //     // Create Jalapeno and clear the row
    //     Jalapeno jalapeno = new Jalapeno("Jalapeno", 100, 0, 0, 150, 0, 20);
    //     jalapeno.clearZombieTile(row);

    //     // Display zombies after clearing
    //     System.out.println("Zombies after Jalapeno clears the row:");
    //     for (int i = 0; i < row.size(); i++) {
    //         System.out.println("Tile " + (i + 1) + " has " + row.get(i).getZombies().size() + " zombies.");
    //     }
    // }
}   
