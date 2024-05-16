package Map;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Map.Map;
import Tanaman.*;
import Zombie.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Map gameMap = new Map(6, 11); 
        Tile tile = new Tile(false, false);

        Thread zombieAdder = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Simulate adding zombies every 5 seconds
                    Thread.sleep(5000);
                    tile.addZombie(new NormalZombie("Normal Zombie"));
                }
            } catch (InterruptedException e) {
                System.out.println("Zombie adding thread interrupted.");
            }
        });

        zombieAdder.start();  // Start the zombie adding thread

        while (true) {
            System.out.println("You have 10 seconds to enter 'addPlant' command (addPlant, display, quit):");
            System.out.println("==============================================");
            System.out.println("|   ADDPLANT    |     DISPLAY   |    QUIT     |");
            System.out.println("==============================================");
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 10000 && !scanner.hasNextLine()) {
                try {
                    Thread.sleep(200); // Check every 200 milliseconds
                } catch (InterruptedException e) {
                    System.out.println("Sleep interrupted");
                }
            }
            if (scanner.hasNextLine()) {
                String command = scanner.next();

                if (command.equalsIgnoreCase("addPlant")) {
                    System.out.println("Enter plant name and cost:");
                    String name = scanner.next();
                    int cost = scanner.nextInt();
                    System.out.println("Enter coordinates (x y):");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    if (name.equalsIgnoreCase("Sunflower")) {
                        gameMap.addPlant(new Sunflower(name, 50, 0, 0, 50, 0, 10), x, y);
                    } 
                    else if (name.equalsIgnoreCase("Peashooter")) {
                        gameMap.addPlant(new Peashooter(name, 50, 0, 0, 50, 0, 10), x, y);
                    } 
                    else if (name.equalsIgnoreCase("Wallnut")) {
                        gameMap.addPlant(new WallNut(name, 50, 0, 0, 50, 0), x, y);
                    }
                    else if (name.equalsIgnoreCase("Snowpea")) {
                        gameMap.addPlant(new SnowPea(name, 50, 0, 0, 50, 0, 10), x, y);
                    } 
                    else if (name.equalsIgnoreCase("Chomper")){
                        gameMap.addPlant(new Chomper(name, y, y, y, cost, x, y), x, y);
                    }
                    else if (name.equalsIgnoreCase("Jalapeno")){
                        gameMap.addPlant(new Jalapeno(name, y, y, y, cost, x, y), x, y);
                    }
                    else if (name.equalsIgnoreCase("Lilypad")){
                        gameMap.equals(new Lilypad(name, y, y, y, cost, x, y));
                    }
                    else if (name.equalsIgnoreCase("Squash")){
                        gameMap.equals(new Squash(name, y, y, y, cost, x, y));
                    }
                    else if (name.equalsIgnoreCase("Sunshroom")){
                        gameMap.equals(new SunShroom(name, y, y, y, cost, x, y));
                    }
                    else if (name.equalsIgnoreCase("Tallnut")){
                        gameMap.equals(new TallNut(name, y, y, y, cost, x, y));
                    }
                    else {
                        System.out.println("Invalid plant name.");
                        continue;
                    }
                    gameMap.displayMap(); // Display the updated game map
                } else if (command.equalsIgnoreCase("display")) {
                    gameMap.displayMap(); // Display the current game map
                } else if (command.equalsIgnoreCase("quit")) {
                    // Interrupt the zombie adding thread and exit the program
                    zombieAdder.interrupt();
                    break;
                }
            }
        }

        scanner.close();
    }
}
