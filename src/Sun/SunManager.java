package Sun;

import java.util.ArrayList;
import java.util.List;
import Tanaman.*;


public class SunManager implements SunListener{
    private List<ProduceSun> producers;
    private int totalSun;

    public SunManager() {
        producers = new ArrayList<>();
        totalSun = 0;
    }

    public void addProducer(ProduceSun producer) {
        if (producer instanceof Sun) {
            ((Sun) producer).setSunListener(this);
        } else if (producer instanceof Sunflower) {
            ((Sunflower) producer).setSunListener(this);
        }
        producers.add(producer);
    }

    @Override
    public void onSunProduced() {
        totalSun = getTotalSun();
        System.out.println("Total Sun: " + totalSun);
    }

    public int getTotalSun() {
        int sum = 0;
        for (ProduceSun producer : producers) {
            sum += producer.getAmount();
        }
        return sum;
    }

    public boolean plant(Plant plant, int cost) {
        for (ProduceSun producer : producers) {
            if (producer instanceof Sun) {
                Sun sun = (Sun) producer;
                if (sun.reduceSun(cost)) {
                    System.out.println("Menanam " + plant.getClass().getSimpleName() + " dengan biaya " + cost);
                    return true;
                } else {
                    System.out.println("Tidak cukup matahari untuk menanam " + plant.getClass().getSimpleName());
                    return false;
                }
            }
        }
        return false;
    }

    public void stopAllSunProduction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopAllSunProduction'");
    }

    // public static void main(String[] args) {
    //     SunManager sunManager = new SunManager();

    //     Sun sun = new Sun(0);
    //     Sunflower sunflower = new Sunflower(null, 0, 0, 0, 0, 0, 0);

    //     sunManager.addProducer(sun);
    //     sunManager.addProducer(sunflower);

    //     sun.startProducingSun();
    //     sunflower.startProducingSun();
    // }
}


