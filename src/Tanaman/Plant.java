package Tanaman;

public class Plant {

    // Mendeklarasikan atribut
    private String name;
    private int cost;
    private int health;
    private int attack_damage;
    private int attack_speed;
    private int range;
    private int cool_down;

    // Method
    public Plant(String name, int cost, int health, int attack_damage, int attack_speed, int range, int cool_down) {

    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attack_damage;
    }

    public int getAttackSpeed() {
        return attack_speed;
    }

    public int getRange() {
        return range;
    }

    public int getCoolDown() {
        return cool_down;
    }
}
