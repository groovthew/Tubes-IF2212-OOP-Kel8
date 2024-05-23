package Tanaman;

import Sun.ProduceSun;
import Sun.SunListener;

public class TwinSunflower extends Plant implements ProduceSun{
    private int sunTotal;
    private boolean makingSun;
    private SunListener listener;

    public TwinSunflower(String name, int health, int attack_damage, int attacks_speed, int cost, int range, int cooldown) {
        super("Twin Sunflower", 100, 0, 0, 50, 0, 15, false);
        this.sunTotal = 0;
    }

    public void setSunListener(SunListener listener) {
        this.listener = listener;
    }

    public void increaseSun(){
        sunTotal += 50;
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
                    Thread.sleep(3500);
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
