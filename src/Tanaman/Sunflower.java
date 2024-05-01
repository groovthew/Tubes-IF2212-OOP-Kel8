package Tanaman;

public class Sunflower extends Plant {
    private int sun;

    public Sunflower(int sun, int interval) {
        super("Sunflower", 50, 100, 0, 0, 0, 10);
        this.sun = 25;
    }

    public int getSun() {
        return sun;
    }
}
