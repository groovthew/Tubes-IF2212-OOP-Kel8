package Tanaman;

public class Sunflower extends Plant {
    private int sun = 25;
    private int interval = 10;

    public Sunflower(int sun, int interval) {
        super("Sunflower", 50, 100, 0, 0, 0, 10);
        this.sun = sun;
        this.interval = interval;
    }

    public int getSun() {
        return sun;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int sun) {
        for(int i = 0; i <= interval; i++) {
            this.sun = sun;
        }
    }
}
