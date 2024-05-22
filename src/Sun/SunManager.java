package Sun;

import java.util.ArrayList;
import java.util.List;
import Tanaman.Sunflower;


public class SunManager implements SunListener{
    private static List<ProduceSun> producers;
    private static int totalSun;

    public SunManager() {
        producers = new ArrayList<>();
        totalSun = 0;
    }
    public synchronized static void decreaseSun(int amount){
        if (useSun(amount)) {
            totalSun -= amount;
        }
        System.out.println(totalSun);
    }
    public void addProducer(ProduceSun producer) {
        producers.add(producer);
        producer.setSunListener(this); 
        updateSunAmount();
    }
    public synchronized static boolean useSun(int amount) {
        
        if (totalSun >= amount) {
            totalSun -= amount;
            return true;
        }
        return false;
    }
    public static synchronized int getTotalSun() {
        return totalSun;
    }

    @Override
    public void onSunProduced() {
        updateSunAmount();
        System.out.println("Total Sun: " + totalSun);
    }

    private void updateSunAmount() {
        int sum = 0; 
        for (ProduceSun producer : producers) {
            sum += producer.getAmount();
        }
        totalSun = sum;
    }

    public static void main(String[] args) {
        SunManager sunManager = new SunManager();

        Sun sun = new Sun(0);
        Sunflower sunflower = new Sunflower(null, 0, 0, 0, 0, 0, 0);

        sunManager.addProducer(sun);
        sunManager.addProducer(sunflower);

        sun.startProducingSun();
        sunflower.startProducingSun();
    }
}


