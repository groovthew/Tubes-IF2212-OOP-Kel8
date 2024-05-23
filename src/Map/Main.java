package Map;

import java.util.Random;
import java.util.Scanner;

import Exceptions.CantBePlantedException;
import Exceptions.CantSwapDeckException;
import Exceptions.DeckIsEmptyException;
import Exceptions.DeckNotFullException;
import Tanaman.*;
import Zombie.*;
import Main.Deck;
import Main.Inventory;
import Main.plantsList;
import Main.zombiesList;
import Main.Help;
import java.util.Scanner;
import Sun.*;

public class Main {
    static String green = "\u001B[32m";  // Kode ANSI untuk warna hijau
    static String red = "\u001B[31m";    // Kode ANSI untuk warna merah
    static String blue = "\u001B[34m";   // Kode ANSI untuk warna biru
    static String yellow = "\033[33m";   // Kode ANSI untuk warna kuning
    static String reset = "\u001B[0m";   // Kode ANSI untuk mereset warna

    private static Scanner scanner = new Scanner(System.in);
    private static Deck deck = new Deck();
    private static Inventory inventory = new Inventory();

    public static void main(String[] args) throws DeckIsEmptyException, DeckNotFullException, CantSwapDeckException {
        Menu printer = new Menu();
        printer.openingAct();
        initializeInventory();

        while (true) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "1":
                    gameMenu();
                    break;
                case "4":
                    new zombiesList();
                    break;
                case "2":
                    new plantsList();
                    break;
                case "5":
                    Help.displayHelp();
                    break;
                case "3":
                    System.out.println(green + "=================" + reset + yellow + " Exiting game. Goodbye!" + reset + green+ "==================" + reset);
                    scanner.close();
                    return;
                default:
                    System.out.println(red + "Invalid choice. Please choose again." + reset);
            }
        }
    }

    public static void initializeInventory() {
        try {
            Plant plant1 = new Plant("Peashooter", 100, 25, 4, 100, -1, 10, false);
            Plant plant2 = new Plant("Sunflower", 50, 0, 0, 50, 0, 10, false);
            Plant plant3 = new Plant("Wall-nut", 1000, 0, 0, 50, 0, 20, false);
            Plant plant4 = new Plant("Snow Pea", 100, 25, 4, 175, -1, 10,false);
            Plant plant5 = new Plant("Chomper", 1000, 1000, 0, 150, 0, 10,false);
            Plant plant6 = new Plant("Squash", 100, 5000, 0, 50, 1, 20, false);
            Plant plant7 = new Plant("Jalapeno", 100, 0, 0, 150, 0, 20, false);
            Plant plant8 = new Plant("Tall-nut", 2000, 0, 0, 100, 0, 20, false);
            Plant plant9 = new Plant("Lilypad", 100, 0, 0, 25, 0, 10, true);
            Plant plant10 = new Plant("Twin Sunflower", 100, 0, 0, 50, 0, 10, false);

            inventory.addPlantToInventory(plant1);
            inventory.addPlantToInventory(plant2);
            inventory.addPlantToInventory(plant3);
            inventory.addPlantToInventory(plant4);
            inventory.addPlantToInventory(plant5);
            inventory.addPlantToInventory(plant6);
            inventory.addPlantToInventory(plant7);
            inventory.addPlantToInventory(plant8);
            inventory.addPlantToInventory(plant9);
            inventory.addPlantToInventory(plant10);
        } catch (Exception e) {
            System.out.println("Error initializing inventory: " + e.getMessage());
        }
    }

    public static void displayMenu() {
        System.out.println(green + "===========================GAME MENU============================" + reset);
        System.out.println(yellow + "|             1. START             |       4. ZOMBIE LIST      |");
        System.out.println("|             2. PLANT LIST        |          5. HELP          |");
        System.out.println("|             3. EXIT              |                           |" + reset);
        System.out.println(green + "================================================================" + reset);
        System.out.print(blue + "Enter your choice: " + reset);
    }

    public static void gameMenu() throws DeckIsEmptyException, DeckNotFullException, CantSwapDeckException {
        while (true) {
            System.out.println(green + "=============================GAME MENU==================================" + reset);
            System.out.println(yellow + "|   1. DISPLAY DECK    |   2. ADD PLANT TO DECK  |   3. REMOVE DECK    |");
            System.out.println("|   4. SWAP DECK       |   5. DISPLAY INVENTORY  |   6. SWAP INVENTORY |");
            System.out.println("|   7. BACK            |   8. START GAME         |                     |" + reset);
            System.out.println(green + "========================================================================" + reset);
            System.out.print(blue + "Enter your choice: " + reset);
    
            String managementChoice = scanner.nextLine();
    
            switch (managementChoice.toLowerCase()) {
                case "1":
                    System.out.println("Deck:");
                    deck.displayDeck();
                    break;
                case "2":
                    if (deck.getDeck().size() >= Deck.MAX_PLANTS) {
                        System.out.println("Deck sudah penuh. Tidak dapat menambahkan tanaman lagi.");
                        System.out.println("Deck:");
                        deck.displayDeck();
                        break;
                    }
                    inventory.displayInventory();
                    System.out.println("Enter the index of the plant to add from inventory:");
                    int plantIndex = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    try {
                        if (plantIndex < 1 || plantIndex >= inventory.getInventory().size()) {
                            System.out.println("Indeks tanaman tidak valid.");
                            break;
                        }
                
                        Plant plant = inventory.getInventory().get(plantIndex-1);
            
                        if (deck.isPlantInDeck(plant.getName())) {
                            System.out.println("Tanaman tersebut sudah ada di deck.");
                            break;
                        }
                        
                        deck.addPlant(plant);
                        System.out.println("Tanaman " + plant.getName() + " berhasil ditambahkan ke deck!");
                    } catch (CantBePlantedException e) {
                        System.out.println(e.getMessage());
                    }
                        break;
                case "3":
                    if (!deck.isDeckEmpty()) {
                        System.out.println("Enter the index of the plant to remove from deck: ");
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        deck.removePlant(index - 1);
                    } else {
                        System.out.println("Deck kosong. Tidak ada tanaman yang bisa dihapus.");
                    }
                    break;
                case "4":
                    if (!deck.isDeckEmpty()) {
                        System.out.println("Enter the positions of the plants to swap (separated by space): ");
                        int position1 = scanner.nextInt();
                        int position2 = scanner.nextInt();
                        scanner.nextLine(); 
                        deck.swapDeck(position1 - 1, position2 - 1);
                    } else {
                        throw new CantSwapDeckException();
                    }
                    break;
                case "5":
                    inventory.displayInventory();
                    break;
                case "6":
                    System.out.println("Enter the positions of the plants to swap (separated by space): ");
                    int pos1 = scanner.nextInt();
                    int pos2 = scanner.nextInt();
                    scanner.nextLine(); 
                    inventory.swapPlants(pos1 - 1, pos2 - 1);
                    break;
                case "7":
                    return;
                default:
                    System.out.println(red + "Invalid choice. Please choose again." + reset);
                case "8":
                    try {
                        if (deck.isDeckEmpty()) {
                            throw new DeckIsEmptyException();
                        } else if (deck.getDeck().size() < Deck.MAX_PLANTS) {
                            throw new DeckNotFullException();
                        } else {
                            startGame(scanner);
                        }
                    } catch (DeckIsEmptyException | DeckNotFullException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }

    public static void startGame(Scanner scanner) {
        Random random = new Random();
        Map gameMap = new Map(6, 11, deck);
        Tile tile = new Tile(false, false);

        Thread zombieAdder = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Simulate adding zombies every 5 seconds
                    Thread.sleep(5000);
                    tile.addZombie(new NormalZombie());
                }
            } catch (InterruptedException e) {
                System.out.println("Zombie adding thread interrupted.");
            }
        });

        zombieAdder.start();  // Start the zombie adding thread

        System.out.println(blue + "ENTER COMMAND" + reset);
            System.out.println(green + "==============================================" + reset);
            System.out.println(yellow + "|  1. PLAY  |   2. DISPLAY   |  3. BACK     |" + reset);
            System.out.println( green+ "==============================================" + reset);

        while (true) {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 10000 && !scanner.hasNextLine()) {
                try {
                    Thread.sleep(200); // Check every 200 milliseconds
                } catch (InterruptedException e) {
                    System.out.println("Sleep interrupted");
                }
            }
            if (scanner.hasNextLine()) {
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("1")) {
                    Map map = new Map(6, 11, deck);
                    map.initiateMap();
                    SunManager sunManager = new SunManager();

                    // Create instances of Sun, Sunflower, and SunShroom
                    Sun sun = new Sun(0);
            
                    // Add producers to SunManager
                    sunManager.addProducer(sun);
            
                    // Start producing sun
                    sun.startProducingSun();
                }    

                if (command.equalsIgnoreCase("2")) {
                    gameMap.displayMap();
                    System.out.println(green + "==============================================" + reset);
                    System.out.println(yellow + "|                    BACK                    |" + reset);
                    System.out.println( green+ "==============================================" + reset);
                    
                }

                if (command.equalsIgnoreCase("3")){
            
                    break;
                }
            }
        }
    }
}
