package Time;

import Map.*;
import Sun.*;

public class RunnableTimer implements Runnable {
    private Sun sun;
    private boolean nightMode = false;

    public RunnableTimer(Map map, Sun sun) {
        this.sun = sun;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100000); // Menunggu 100 detik (100000 milidetik)
            nightMode = true;
            sun.stopProducingSun();
            System.out.println("Night mode activated!");
            // Anda bisa menambahkan logika lainnya untuk mengubah tampilan map menjadi night mode
            // Misalnya, mengubah warna latar belakang, mengurangi pencahayaan, atau menambahkan efek visual lainnya.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isNightMode() {
        return nightMode;
    }
}
