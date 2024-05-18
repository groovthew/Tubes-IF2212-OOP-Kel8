package Map;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Exceptions.CantBePlantedException;
import Map.Map;
import Tanaman.*;
import Zombie.*;
import Main.Deck;
import Main.Inventory;
import Main.Help;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Deck deck = new Deck();
    private static Inventory inventory = new Inventory();
    public static void main(String[] args) {
        Menu printer = new Menu();
        printer.openingAct();
        initializeInventory();

        while (true) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "start":
                    startGame(scanner);
                    break;
                case "inventory":
                    // Implement inventory functionality
                    manageInventory();
                    break;
                case "deck":
                    // Implement deck functionality
                    manageDeck();
                    if (deck.getDeck().size() >= Deck.MAX_PLANTS) {
                        System.out.println("Deck telah terisi penuh:");
                    }
                    break;
                case "help":
                    // Implement help functionality
                    Help.displayHelp();
                    break;
                case "exit":
                    System.out.println("Exiting game. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void initializeInventory() {
        try {
            Plant plant1 = new Plant("Peashooter", 50, 20, 1, 50, 1, 5, false);
            Plant plant2 = new Plant("Sunflower", 30, 0, 0, 25, 1, 7, false);
            Plant plant3 = new Plant("Walnut", 150, 0, 0, 50, 1, 10, false);
            Plant plant4 = new Plant("SnowPea", 100, 25, 4, 175, -1, 10, false);
            Plant plant5 = new Plant("Chomper", 1000, 1000, 0, 150, 0, 10, false);
            Plant plant6 = new Plant("Squash", 100, 5000, 0, 50, 1, 20, false);
            Plant plant7 = new Plant("Jalapeno", 100, 0, 0, 150, 0, 20, false);
            Plant plant8 = new Plant("Tall-nut", 2000, 0, 0, 100, 0, 20, false);
            Plant plant9 = new Plant("Lilypad", 100, 25, 4, 100, -1, 10, true);
            Plant plant10 = new Plant("Sunshroom", 100, 0, 0, 15, 0, 5, false);

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
        System.out.println("=============GAME MENU=============");
        System.out.println("|    START     |   INVENTORY   |");
        System.out.println("|    DECK      |     HELP      |");
        System.out.println("|    EXIT      |");
        System.out.println("===================================");
        System.out.print("Enter your choice: ");
    }

    public static void manageInventory() {
        while (true) {
            System.out.println("=============INVENTORY MENU=============");
            System.out.println("|  DISPLAY INVENTORY  |  SWAP INVENTORY  |");
            System.out.println("|                    BACK                |");
            System.out.println("========================================"); 

            System.out.print("Enter your choice: ");
            String inventoryChoice = scanner.nextLine();

        switch (inventoryChoice.toLowerCase()) {
            case "display inventory":
                // Menampilkan isi inventory
                inventory.displayInventory();
                break;
            case "swap inventory":
                System.out.println("Enter the positions of the plants to swap (separated by space): ");
                int position1 = scanner.nextInt();
                int position2 = scanner.nextInt();
                scanner.nextLine(); 
                inventory.swapPlants(position1 - 1, position2 - 1);
                break;
            case "back":
                return;
            default:
                System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void manageDeck() {
        while (true) {
            System.out.println("=============DECK MENU=============");
            System.out.println("|    SWAP DECK   |  REMOVE DECK   |");
            System.out.println("|    ADD PLANT   |  DISPLAY DECK  |"); 
            System.out.println("|      BACK      |                |");
            System.out.println("===================================");
            System.out.print("Pilih menu yang diinginkan: ");
            String deckChoice = scanner.nextLine();
    
            switch (deckChoice.toLowerCase()) {
                case "swap deck":
                if (!deck.isDeckEmpty()) {
                    System.out.println("Enter the positions of the plants to swap (separated by space): ");
                    int position1 = scanner.nextInt();
                    int position2 = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan newline dari buffer
                    
                    // Panggil metode swapDeck dari deck
                    deck.swapDeck(position1 - 1, position2 - 1);
                } else {
                    System.out.println("Deck kosong. Tidak ada tanaman yang bisa ditukar.");
                }
                    break;
                case "remove deck":
                if (!deck.isDeckEmpty()) {
                    System.out.println("Enter the index of the plant to remove from deck: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    deck.removePlant(index - 1);
                } else {
                    System.out.println("Deck kosong. Tidak ada tanaman yang bisa dihapus.");
                }
                    break;
                case "add plant":
                    // Memeriksa apakah deck sudah penuh
                    if (deck.getDeck().size() >= Deck.MAX_PLANTS) {
                        System.out.println("Deck sudah penuh. Tidak dapat menambahkan tanaman lagi.");
                        break;
                    }
                    // Menampilkan inventory
                    inventory.displayInventory();
                    System.out.println("Enter the name of the plant to add from inventory:");
                    String plantName = scanner.nextLine();
                    try {
                        // Memeriksa apakah tanaman sudah ada di deck
                        if (deck.isPlantInDeck(plantName)) {
                            System.out.println("Tanaman tersebut sudah ada di deck.");
                            break;
                        }
                        // Menambahkan tanaman ke deck
                        deck.addPlantFromInventory(plantName, inventory);
                    } catch (CantBePlantedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "display deck":
                    // Menampilkan isi deck
                    deck.displayDeck();
                    break;
                case "back":
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }    

    public static void startGame(Scanner scanner) {
        Random random = new Random();
        Map gameMap = new Map(6, 11);
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
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("addPlant")) {
                    System.out.println("Enter plant name and cost:");
                    String name = scanner.next();
                    int cost = scanner.nextInt();
                    System.out.println("Enter coordinates (x y):");
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    if (name.equalsIgnoreCase("Sunflower")) {
                        gameMap.addPlant(new Sunflower(name, 50, 0, 0, 50, 0, 10), x, y);
                    } else if (name.equalsIgnoreCase("Peashooter")) {
                        gameMap.addPlant(new Peashooter(name, 50, 0, 0, 50, 0, 10), x, y);
                    } else if (name.equalsIgnoreCase("Wallnut")) {
                        gameMap.addPlant(new WallNut(name, 50, 0, 0, 50, 0), x, y);
                    } else if (name.equalsIgnoreCase("Snowpea")) {
                        gameMap.addPlant(new SnowPea(name, 50, 0, 0, 50, 0, 10), x, y);
                    } else if (name.equalsIgnoreCase("Chomper")) {
                        gameMap.addPlant(new Chomper(name, y, y, y, cost, x, y), x, y);
                    } else if (name.equalsIgnoreCase("Jalapeno")) {
                        gameMap.addPlant(new Jalapeno(name, y, y, y, cost, x, y), x, y);
                    } else if (name.equalsIgnoreCase("Lilypad")) {
                        gameMap.addPlant(new Lilypad(name, y, y, y, cost, x, y), y, y);
                    } else if (name.equalsIgnoreCase("Squash")) {
                        gameMap.addPlant(new Squash(name, y, y, y, cost, x, y), y, y);
                    } else if (name.equalsIgnoreCase("Sunshroom")) {
                        gameMap.addPlant(new SunShroom(name, y, y, y, cost, x, y), y, y);
                    } else if (name.equalsIgnoreCase("Tallnut")) {
                        gameMap.addPlant(new TallNut(name, y, y, y, cost, x, y), y, y);
                    } else {
                        System.out.println("Invalid plant name.");
                        continue;
                    }
                    gameMap.displayMap(); // Display the updated game map
                } else if (command.equalsIgnoreCase("display")) {
                    gameMap.displayMap(); // Display the current game map
                } else if (command.equalsIgnoreCase("quit")) {
                    // Interrupt the zombie adding thread and exit the game loop
                    zombieAdder.interrupt();
                    break;
                }
            }
        }
    }
}
