package Tanaman;

import Sun.ProduceSun;

public class SunShroom extends Plant implements ProduceSun {
    private int amount;

    public SunShroom(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Sun-Shroom", 100, 0, 0, 15, 0, 5);
    }

    @Override
    public void increaseSun() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'increaseSun'");
    }

    @Override
    public int getAmount() {
       return amount;
    }
}
