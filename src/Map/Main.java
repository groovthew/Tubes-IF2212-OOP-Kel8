package Map;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Tanaman.Peashooter;
import Zombie.NormalZombie;
import Map.Tile;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map gameMap = new Map(5, 5);
        Random random = new Random();
        Tile tile = new Tile(false, new ArrayList<>(), null);

        Thread zombieAdder = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Simulate adding zombies every 5 seconds
                    Thread.sleep(5000);
                    tile.addZombie(new NormalZombie(null, 0, 0, 0));
                }
            } catch (InterruptedException e) {
                System.out.println("Zombie adding thread interrupted.");
            }
        });

        zombieAdder.start();  // Start the zombie adding thread

        while (true) {
            System.out.println("You have 10 seconds to enter 'addPlant' command (addPlant, display, quit):");
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
                    gameMap.addPlant(new Peashooter(name, y, y, y, cost, x, y), x, y);
                    gameMap.displayMap(); // Display the updated game map
                } else if (command.equalsIgnoreCase("display")) {
                    gameMap.displayMap(); // Display the current game map
                } else if (command.equalsIgnoreCase("quit")) {
                    // Interrupt the zombie adding thread and exit the program
                    zombieAdder.interrupt();
                    break;
                } else {
                    System.out.println("Invalid command. Please enter 'addPlant', 'display', or 'quit'.");
                }
            }
        }
        scanner.close();
    }
}
