package Tanaman;

import java.util.Random;

import Sun.ProduceSun;
import Sun.Sun;

public class Sunflower extends Plant implements ProduceSun{
    private int sunTotal;
    private boolean makinSun;

    public Sunflower(String name, int health, int attack_damage, int attacks_speed, int cost, int range, int cooldown) {
        super("Sunflower", 50, 0, 0, 50, 0, 10, false);
        Sun sun = new Sun(0);
        this.sunTotal = sun.getTotalSun();
        startProducingSun();
    }

    public void increaseSun(){
        sunTotal += 25;
        makinSun = true;
    }

    public int getAmount(){
        return this.sunTotal;
    }

    public void startProducingSun(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000); // Konversi interval detik ke milidetik
                        synchronized (this) {
                            increaseSun(); // Menambahkan nilai sebesar 25
                            System.out.println("Sun : " + getAmount());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void stopProducingSun(){
        makinSun = false;
    }

    // public boolean isIncrease(){
    //     return
    // }

    public static void main(String[] args) {
        Sunflower sunflower1 = new Sunflower(null, 0, 0, 0, 0, 0, 0);
        Sun sun = new Sun(0);
        
    }
}
