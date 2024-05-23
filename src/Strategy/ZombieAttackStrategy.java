package Strategy;

import java.util.Timer;
import java.util.TimerTask;

import Map.Tile;
import Zombie.Zombie;
import Tanaman.Plant;

public class ZombieAttackStrategy implements AttackStrategy {
    private Tile[][] tiles;
    private Zombie zombie;
    private int row;
    private int col;
    private Timer timer;

    public ZombieAttackStrategy(Tile[][] tiles, Zombie zombie, int row, int col) {
        this.tiles = tiles;
        this.zombie = zombie;
        this.row = row;
        this.col = col;
        this.timer = new Timer();
    }

    @Override
    public void attack() {
        if (!zombie.isCooldown()){    
            if (col > 0 && !tiles[row][col - 1].getPlants().isEmpty()) {
                Plant plant = tiles[row][col - 1].getPlants().get(0);
                plant.setHealth(plant.getHealth() - zombie.getAttackDamage());
                if (plant.getHealth() <= 0) {
                    tiles[row][col - 1].getPlants().remove(plant);
                }
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    zombie.setIsCooldown(false);
                }
            }, (long) (zombie.getAttackSpeed() * 1000));
        }
    }
}
