package Tanaman;

import Sun.ProduceSun;
import Sun.SunListener;

public class TwinSunflower extends Plant implements ProduceSun {
    private int sunTotal;
    private boolean makingSun;
    private SunListener listener;

    public TwinSunflower(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown) {
        super("Twin Sunflower", 100, 0, 0, 125, 0, 15, false);
        this.sunTotal = 0;
    }

    public void setSunListener(SunListener listener) {
        this.listener = listener;
    }

    @Override
    public void increaseSun() {
        sunTotal += 50;
        if (listener != null) {
            listener.onSunProduced();
        }
    }

    @Override
    public boolean reduceSun(int cost) {
        if (sunTotal >= cost) {
            sunTotal -= cost;
            if (listener != null) {
                listener.onSunProduced();
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getAmount() {
        return sunTotal;
    }

    @Override
    public void startProducingSun() {
        if (!makingSun) {
            makingSun = true;
            new Thread(() -> {
                while (makingSun) {
                    try {
                        Thread.sleep(3000);
                        increaseSun();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void stopProducingSun() {
        makingSun = false;
    }
}
