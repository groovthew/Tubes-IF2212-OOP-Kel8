package Sun;

import Map.Map;
import java.util.Random;

public class Sun implements ProduceSun {
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

    @Override
    public void increaseSun() {
        totalSun += 25;
        if (listener != null) {
            listener.onSunProduced();
        }
    }

    @Override
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
    public void startProducingSun(Map map) {
        sunProductionThread = new Thread(() -> {
            Random random = new Random();
            while (!map.gameOver()) {
                try {
                    Thread.sleep((random.nextInt(6) + 5) * 1000);
                    increaseSun();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println(" Sun berhenti.");
        });
        sunProductionThread.start();
    }

    @Override
    public void stopProducingSun() {
        makingSun = false;
        sunProductionThread.interrupt();
    }
}
