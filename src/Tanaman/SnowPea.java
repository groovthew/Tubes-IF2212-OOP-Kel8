package Tanaman;

import Map.Tile;
import Zombie.Zombie;

import java.util.Timer;
import java.util.TimerTask;

public class SnowPea extends Plant {

    public SnowPea(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Snow Pea", 100, 1, 4, 175, -1, 10, false);
    }

    public void attackZombie(Tile[][] tiles, int i, int j) {
        for (int col = j; col < tiles[i].length; col++) {
            if (!tiles[i][col].getZombies().isEmpty()) {
                Zombie targetZombie = tiles[i][col].getZombies().get(0);
                targetZombie.setHealth(targetZombie.getHealth() - this.getAttackDamage());
                if (targetZombie.getHealth() <= 0) {
                    tiles[i][col].getZombies().remove(targetZombie);
                } else {
                    applySlowEffect(targetZombie);
                }
                break;
            }
        }
    }

    private void applySlowEffect(Zombie zombie) {
        if (!zombie.isSlowed()) {
            zombie.setSlowed(true);
            double originalSpeed = zombie.getSpeed();
            double originalAttackSpeed = zombie.getAttackSpeed();
            zombie.setSpeed(originalSpeed * 0.5);
            zombie.setAttackSpeed(originalAttackSpeed * 0.5);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    zombie.setSpeed(originalSpeed);
                    zombie.setAttackSpeed(originalAttackSpeed);
                    zombie.setSlowed(false);
                }
            }, 3000);
        }
    }
}
