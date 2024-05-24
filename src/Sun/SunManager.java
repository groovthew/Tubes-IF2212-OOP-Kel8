package Sun;

import java.util.ArrayList;
import java.util.List;
import Tanaman.Plant;
import Tanaman.Sunflower;
import Tanaman.TwinSunflower;

public class SunManager implements SunListener {
    private List<ProduceSun> producers;
    private int totalSun;
    static String red = "\u001B[31m";
    static String reset = "\u001B[0m";

    public SunManager() {
        producers = new ArrayList<>();
        totalSun = 50; // Memulai dengan 50 sun
    }

    public void addProducer(ProduceSun producer) {
        if (producer instanceof Sun) {
            ((Sun) producer).setSunListener(this);
        } else if (producer instanceof Sunflower) {
            ((Sunflower) producer).setSunListener(this);
        } else if (producer instanceof TwinSunflower) {
            ((TwinSunflower) producer).setSunListener(this);
        }
        producers.add(producer);
        producer.startProducingSun(null);
    }

    @Override
    public void onSunProduced() {
        totalSun = getTotalSun();
        
    }

    public int getTotalSun() {
        int sum = 0;
        for (ProduceSun producer : producers) {
            sum += producer.getAmount();
        }
        return sum;
    }

    public boolean plant(Plant plant, int cost) {
        return reduceSun(cost);
    }
    

    public void stopAllSunProduction() {
        for (ProduceSun producer : producers) {
            producer.stopProducingSun();
        }
    }

    public boolean reduceSun(int cost) {
        if (totalSun >= cost) {
            totalSun -= cost;
            System.out.println("Sun berkurang sebanyak: " + cost + ", Total Sun sekarang: " + totalSun);
            return true;
        } else {
            System.out.println(red + "Tidak cukup matahari. Total Sun: " + totalSun + reset);
            return false;
        }
    }
    

    public static void main(String[] args) {
        SunManager sunManager = new SunManager();

        Sun sun = new Sun(50);
        Sunflower sunflower = new Sunflower(null, 0, 0, 0, 0, 0, 0);
        TwinSunflower twinSunflower = new TwinSunflower(null, 0, 0, 0, 0, 0, 0);

        sunManager.addProducer(sun);
        sunManager.addProducer(sunflower);
        sunManager.addProducer(twinSunflower);
    }
}
