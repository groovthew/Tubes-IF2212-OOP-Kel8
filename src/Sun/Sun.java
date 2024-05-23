package Sun;

import java.util.Random;

public class Sun implements ProduceSun{
    private int totalSun;
    private boolean makingSun;
    private Thread sunProductionThread;
    private SunListener listener;
    
    public Sun(int totalSun) {
        this.totalSun = totalSun;
    }

    public void setSunListener(SunListener listener) {
        this.listener = listener;
    }

    @Override
    public int getAmount() {
        return totalSun;
    }

    public void increaseSun() {
        totalSun += 25;
        if (listener != null) {
            listener.onSunProduced();
        }
    }

    public boolean reduceSun(int cost) {
        if (totalSun >= cost) {
            totalSun -= cost;
            if (listener != null) {
                listener.onSunProduced();
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void startProducingSun() {
        if (!makingSun) {
            makingSun = true;
            sunProductionThread = new Thread(() -> {
                Random random = new Random();
                while (makingSun) {
                    try {
                        Thread.sleep((random.nextInt(6) + 5) * 1000);
                        increaseSun();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            sunProductionThread.start();
        }
    }

    @Override
    public void stopProducingSun() {
        makingSun = false;
        sunProductionThread.interrupt();
    }
}

