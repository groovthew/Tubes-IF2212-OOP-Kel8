package Main;
import Tanaman.*;

public class PlantsList {
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
    static String reset = "\u001B[0m";  // Kode ANSI untuk mereset warna
    static String green = "\u001B[32m";  // Kode ANSI untuk warna hijau

    public PlantsList() {
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


        System.out.println(yellow + "===========================PLANTS LIST==========================" + reset);
        System.out.println(" ");
        System.out.println(yellow + "1. PEASHOOTER" + reset);
        printPlantDetails(peashooter);
        System.out.println(yellow + "2. SUNFLOWER" + reset);
        printPlantDetails(sunflower);
        System.out.println(yellow + "3. LILYPAD" + reset);
        printPlantDetails(lilypad);
        System.out.println(yellow + "4. CHOMPER" + reset);
        printPlantDetails(chomper);
        System.out.println(yellow + "5. TALL-NUT" + reset);
        printPlantDetails(tallNut);
        System.out.println(yellow + "6. WALL-NUT" + reset);
        printPlantDetails(wallNut);
        System.out.println(yellow + "7. SNOW PEA" + reset);
        printPlantDetails(snowpea);
        System.out.println(yellow + "8. SQUASH" + reset);
        printPlantDetails(squash);
        System.out.println(yellow + "9. JALAPENO" + reset);
        printPlantDetails(jalapeno);
        System.out.println(yellow + "10. TWIN SUNFLOWER" + reset);
        printPlantDetails(twinSunflower);
        System.out.println(yellow + "================================================================" + reset);
    }

    public void printPlantDetails(Plant plant) {
        System.out.println(green + "Name: " + reset + plant.getName());
        System.out.println(green + "Cost: " + reset + plant.getCost());
        System.out.println(green + "Health: " + reset + plant.getHealth());
        System.out.println(green + "Attack Damage: " + reset + plant.getAttackDamage());
        System.out.println(green + "Attack Speed: " + reset + plant.getAttackSpeed());
        System.out.println(green + "Range: " + reset + plant.getRange());
        System.out.println(green + "Cooldown: " + reset + plant.getCooldown());
        System.out.println(" ");
    }
}
