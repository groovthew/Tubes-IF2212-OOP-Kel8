package Main;
import Tanaman.*;

public class plantsList {
    Plant plant;
    Lilypad lilypad;
    Sunflower sunflower;
    Peashooter peashooter;
    Chomper chomper;
    TallNut tallNut;
    WallNut wallNut;
    SnowPea snowpea;
    Squash squash;
    Jalapeno jalapeno;
    TwinSunflower twinSunflower;

    static String yellow = "\033[33m";   // Kode ANSI untuk warna kuning
    static String reset = "\u001B[0m";   // Kode ANSI untuk mereset warna
    public plantsList() {
        peashooter = new Peashooter("Peashooter", 100, 25, 4, 100, -1, 0);
        sunflower = new Sunflower("Sunflower", 50, 30, 0, 0, 0, 5);
        lilypad = new Lilypad("Lilypad", 25, 20, 0, 0, 0, 5);
        chomper = new Chomper("Chomper", 1000, 1000, 0, 150, 0, 10);
        tallNut = new TallNut("TallNut", 125, 200, 0, 0, 0, 20);
        chomper = new Chomper("Wall-nut", 1000, 0, 0, 50, 0, 20);
        tallNut = new TallNut(null, 0, 0, 0, 0, 0, 0);
        wallNut = new WallNut(null, 0, 0, 0, 0, 0);
        snowpea = new SnowPea(null, 0, 0, 0, 0, 0, 0);
        squash = new Squash(null, 0, 0, 0, 0, 0, 0);
        jalapeno = new Jalapeno(null, 0, 0, 0, 0, 0, 0);
        twinSunflower = new TwinSunflower(null, 0, 0, 0, 0, 0, 0);
        

        System.out.println(yellow + "============= PLANT LIST =============" + reset);
        System.out.println("1. Peashooter");
        printPlantDetails(peashooter);
        System.out.println("2. Sunflower");
        printPlantDetails(sunflower);
        System.out.println("3. Lilypad");
        printPlantDetails(lilypad);
        System.out.println("4. Chomper");
        printPlantDetails(chomper);
        System.out.println("5. Tall-Nut");
        printPlantDetails(tallNut);
        System.out.println("6. Wall-Nut");
        printPlantDetails(wallNut);
        System.out.println("7. Snow Pea");
        printPlantDetails(snowpea);
        System.out.println("8. Squash");
        printPlantDetails(squash);
        System.out.println("9. Jalapeno");
        printPlantDetails(jalapeno);
        System.out.println("10. Twin Sunflower");
        printPlantDetails(twinSunflower);
    }

    public void printPlantDetails(Plant plant) {
        System.out.println("Name: " + plant.getName());
        System.out.println("Cost: " + plant.getCost());
        System.out.println("Health: " + plant.getHealth());
        System.out.println("Attack Damage: " + plant.getAttackDamage());
        System.out.println("Attack Speed: " + plant.getAttackSpeed());
        System.out.println("Range: " + plant.getRange());
        System.out.println("Cooldown: " + plant.getCooldown());
        System.out.println(" ");
    }

    public static void main(String[] args) {
        new plantsList();
    }
}
