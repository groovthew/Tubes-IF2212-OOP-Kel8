package Tanaman;

public class DisplayTanaman {
    public static void main(String[] args) {
        Plant myPlant = new Plant("Lilypad", 100, 0, 0, 25, 0, 10,false);
        System.out.println("Name of the plant: " + myPlant.getName());
    }
}
