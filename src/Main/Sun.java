package Main;

public class Sun {
    private int totalSun;
    
    public Sun(int totalSun) {
        this.totalSun = 25;
    }

    public int getTotalSun() {
        return totalSun;
    }

    public void addTotalSun() {
        totalSun += totalSun;
    }
}

