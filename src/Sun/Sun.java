package Sun;

import java.util.Random;

public class Sun implements ProduceSun{
    private int totalSun;
    private boolean makingSun;
    private Thread sunProductionThread;
    
    public Sun(int totalSun) {
        this.totalSun = totalSun;
        startProducingSun();
    }


    public int getTotalSun() {
        return totalSun;
    }

    public void increaseSun() {
        totalSun += 25;
    }


    @Override
    public int getAmount() {
        return totalSun;
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

    public static void main(String[] args) {
        Sun sun = new Sun(0);
        sun.startProducingSun(); 
        
        while (true) {
            // Tunggu beberapa saat agar produksi matahari dimulai sebelum mencetak total matahari
            try {
                Thread.sleep(6000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Cetak jumlah total matahari
            System.out.println("Sun: " + sun.getAmount());
        }
    }   
}

