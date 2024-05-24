package Strategy;

import Map.Tile;
import Tanaman.Plant;
import Tanaman.Peashooter;
import Tanaman.SnowPea;
import Tanaman.Chomper;
import Tanaman.Squash;
import Tanaman.Jalapeno;
import Tanaman.Lilypad;
import Zombie.Zombie;

import java.util.Timer;
import java.util.TimerTask;

public class PlantAttackStrategy implements AttackStrategy {
    private Tile[][] tiles;
    private Plant plant;
    private int row;
    private int col;
    private Timer timer;

    public PlantAttackStrategy(Tile[][] tiles, Plant plant, int row, int col) {
        this.tiles = tiles;
        this.plant = plant;
        this.row = row;
        this.col = col;
        this.timer = new Timer();
    }

    @Override
    public void attack() {
        if (plant instanceof Lilypad) {
            Plant plantOnTop = ((Lilypad) plant).getPlantOnTop();
            if (plantOnTop != null) {
                plant = plantOnTop;
            } else {
                return; // If there's no plant on top, return without attacking
            }
        }
        
        if (plant instanceof Peashooter) {
            Peashooter peashooter = (Peashooter) plant;
            if (!peashooter.isCooldown()){
                Zombie targetZombie = null;
                for (int j = col; j < tiles[row].length; j++) {
                    if (!tiles[row][j].getZombies().isEmpty()) {
                        targetZombie = tiles[row][j].getZombies().get(0);
                        targetZombie.setHealth(targetZombie.getHealth() - plant.getAttackDamage());
                        peashooter.setCooldown(true);
                        if (targetZombie.getHealth() <= 0) {
                            tiles[row][j].getZombies().remove(targetZombie);
                        }
                        break;
                    }
                }
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        peashooter.setCooldown(false);
                    }
                }, peashooter.getShootingCooldown() * 1000);
            }
        } 
        
        else if (plant instanceof SnowPea) {
            SnowPea snowPea = (SnowPea) plant;
            if (!snowPea.isCooldown()){
                Zombie targetZombie = null;
            for (int j = col; j < tiles[row].length; j++) {
                if (!tiles[row][j].getZombies().isEmpty()) {
                    targetZombie = tiles[row][j].getZombies().get(0);
                    targetZombie.setHealth(targetZombie.getHealth() - plant.getAttackDamage());
                    if (targetZombie.getHealth() <= 0) {
                        tiles[row][j].getZombies().remove(targetZombie);
                    } else {
                        applySlowEffect(targetZombie);
                    }
                    break;
                }
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    snowPea.setCooldown(false);
                }
            }, snowPea.getShootingCooldown() * 1000);
            }
        } 
        
        else if (plant instanceof Chomper) {
            Chomper chomper = (Chomper) plant;
            if (!chomper.isEating()) {
                if (col < tiles[row].length - 1 && !tiles[row][col + 1].getZombies().isEmpty()) {
                    tiles[row][col + 1].getZombies().clear();
                    chomper.setEating(true);

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            chomper.setEating(false);
                        }
                    }, chomper.getEatingCooldown() * 1000);
                    System.out.println("Chomper attacked zombies on the right tile [" + row + "][" + (col + 1) + "] and is now on cooldown.");
                }
            }
        } 
        
        else if (plant instanceof Squash) {
            Squash squash = (Squash) plant;
            if (col > 0 && !tiles[row][col - 1].getZombies().isEmpty()) {
                tiles[row][col - 1].getZombies().clear();
                tiles[row][col].getPlants().remove(squash);
                return;
            }
            if (col < tiles[row].length - 1 && !tiles[row][col + 1].getZombies().isEmpty()) {
                tiles[row][col + 1].getZombies().clear();
                tiles[row][col].getPlants().remove(squash);
                return;
            }
            if (!tiles[row][col].getZombies().isEmpty()) {
                tiles[row][col].getZombies().clear();
                tiles[row][col].getPlants().remove(squash);
            }
        } 
        
        else if (plant instanceof Jalapeno) {
            Jalapeno jalapeno = (Jalapeno) plant;
            for (int j = 0; j < tiles[row].length; j++) {
                if (!tiles[row][j].getZombies().isEmpty()) {
                    tiles[row][j].getZombies().clear();
                }
            }
            tiles[row][col].getPlants().remove(jalapeno);
        }
    }

    private void applySlowEffect(Zombie zombie) {
        if (!zombie.isSlowed()) {
            zombie.setSlowed(true);
            double originalSpeed = zombie.getSpeed();
            double originalAttackSpeed = zombie.getAttackSpeed();
            zombie.setSpeed(originalSpeed + originalAttackSpeed * 0.5);
            zombie.setAttackSpeed(originalAttackSpeed + originalAttackSpeed * 0.5);

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
