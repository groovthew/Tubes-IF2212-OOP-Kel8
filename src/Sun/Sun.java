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
    // public void startProducingSun(Map map) {
    //     if (!makingSun) {
    //         makingSun = true;
    //         sunProductionThread = new Thread(() -> {
    //             Random random = new Random();
    //             while (makingSun) {
    //                 try {
    //                     // Calculate dynamic sleep time, for example, 5 to 10 seconds
    //                     int sleepTime = (random.nextInt(6) + 5) * 1000;
    //                     Thread.sleep(sleepTime);
    //                     increaseSun(); 
                        

    //                     // Check if zombies have reached the base or if no zombies are left after 160 seconds
    //                     if (map.gameOver()) {
    //                         makingSun = false; // Stop producing sun due to game conditions
    //                         break; // Exit the loop
    //                     }
    //                 } catch (InterruptedException e) {
    //                     System.out.println("Sun production thread was interrupted.");
    //                     makingSun = false; // Ensure flag is updated to stop the thread
    //                     Thread.currentThread().interrupt(); // Properly handle thread interruption
    //                 }
    //             }
    //         });
    //         sunProductionThread.start();
    //     }
    // }

    @Override
    public void stopProducingSun() {
        makingSun = false;
        sunProductionThread.interrupt();
    }
}

