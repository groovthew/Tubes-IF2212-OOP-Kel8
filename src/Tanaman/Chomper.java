package Tanaman;

public class Chomper extends Plant {
    private boolean isEating; // Untuk menandai apakah Chomper sedang makan atau tidak
    private int eatingCooldown; // Waktu cooldown setelah Chomper makan

    public Chomper(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Chomper", 1000, 1000, 0, 150, 0, 10,false);
        this.isEating = false;
        this.eatingCooldown = 0;
    }

    public void eatingCoolDown() {
        if (isEating) {
            eatingCooldown--;
            if (eatingCooldown <= 0) {
                isEating = false;
            }
        }
    }
}
