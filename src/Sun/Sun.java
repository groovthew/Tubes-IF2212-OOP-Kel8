package Sun;

public class Sun implements ProduceSun{
    private int totalSun;
    private boolean makingSun;
    
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
        makingSun = true;
        new Thread(() -> {
            while (makingSun) {
                try {
                    Thread.sleep((long) (Math.random() * 6000 + 5000));
                    increaseSun();
                } catch (InterruptedException e){}
            }
        }).start();
    }

    @Override
    public void stopProducingSun() {
        makingSun = false;
    }

    public static void main(String[] args) {
        Sun sun = new Sun(0);
        sun.startProducingSun(); // Memulai produksi matahari
        
        // Loop untuk mencetak jumlah total matahari secara berulang
        while (true) {
            // Tunggu beberapa saat agar produksi matahari dimulai sebelum mencetak total matahari
            try {
                Thread.sleep((long) (Math.random() * 6000 + 5000)); // Tunggu antara 5-10 detik
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Cetak jumlah total matahari
            System.out.println("Total sun: " + sun.getAmount());
        }
    }
    
}

