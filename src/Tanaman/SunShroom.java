package Tanaman;

import java.util.Random;

import Sun.ProduceSun;
import Sun.SunListener;

// import Sun.ProduceSun;

public class SunShroom extends Plant implements ProduceSun{
    private int sunTotal;
    private boolean makingSun;
    private SunListener listener;

    public SunShroom(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Sun-Shroom", 100, 0, 0, 15, 0, 5, false);
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
            Random random = new Random();
            while (true) {
                try {
                    Thread.sleep((random.nextInt(6) + 5) * 1000);
                    synchronized (this) {
                        increaseSun();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void stopProducingSun(){
        makingSun = false;
    }
}
