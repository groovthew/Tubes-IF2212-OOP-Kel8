package Tanaman;

import java.util.Random;

import Sun.ProduceSun;
import Sun.Sun;
import Sun.SunListener;

public class Sunflower extends Plant implements ProduceSun{
    private int sunTotal;
    private boolean makingSun;
    private SunListener listener;

    public Sunflower(String name, int health, int attack_damage, int attacks_speed, int cost, int range, int cooldown) {
        super("Sunflower", 50, 0, 0, 50, 0, 10, false);
        this.sunTotal = 0;
    }

    public void setSunListener(SunListener listener) {
        this.listener = listener;
    }

    public void increaseSun(){
        sunTotal += 25;
        makingSun = true;
        if (listener != null) {
            listener.onSunProduced();
        }
    }

    @Override
    public int getAmount(){
        return sunTotal;
    }

    @Override
    public void startProducingSun(){
        Thread thread = new Thread(() -> {
            while (makingSun) {
                try {
                    Thread.sleep(3000);
                    synchronized (this) {
                        increaseSun();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        makingSun = true;
        thread.start();
    }

    @Override
    public void stopProducingSun(){
        makingSun = false;
    }
}
