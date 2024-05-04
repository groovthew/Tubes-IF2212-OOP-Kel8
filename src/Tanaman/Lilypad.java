package Tanaman;

public class Lilypad extends Plant {
    public Lilypad(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Lilypad", 100, 0, 0, 25, 0, 10);
    }

    public static void main (String[] args) {
        Lilypad lilypad = new Lilypad("Lilypad", 100, 0, 0, 25, 0, 10);
        System.out.println("Cost of plant:  " + lilypad.getCost());
        System.out.println("Name of plant: " + lilypad.getName());
    }
}


