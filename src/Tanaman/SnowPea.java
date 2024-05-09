package Tanaman;


// import Zombie.Zombie;

public class SnowPea extends Plant {
    public SnowPea(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Snow Pea", 100, 25, 4, 175, -1, 10);
    }

    // public void shootSnowPea(Tile tile) {
    //     if (tile.hasZombie()) {
    //         Zombie zombie = tile.getZombie();
    //         System.out.println("Snow Pea shoots a frozen pea at the zombie!");
    //         zombie.takeDamage(getAttackDamage()); // gambaran
    //         zombie.freeze(); // ini harus ada method ini di zombie(?)
    //     } else {
    //         System.out.println("No zombie on this tile to shoot at.");
    //     }
    // }

    public static void main(String[] args) {
        SnowPea snowPea = new SnowPea("Snow Pea", 100, 25, 4, 175, -1, 10);
        System.out.println("Name: " + snowPea.getName());
        System.out.println("Health of the plant is: " + snowPea.getHealth());
    }
}
