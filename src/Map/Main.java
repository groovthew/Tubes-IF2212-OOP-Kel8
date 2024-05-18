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
    static String green = "\u001B[32m";  // Kode ANSI untuk warna hijau
    static String red = "\u001B[31m";    // Kode ANSI untuk warna merah
    static String blue = "\u001B[34m";   // Kode ANSI untuk warna biru
    static String yellow = "\033[33m";   // Kode ANSI untuk warna kuning
    static String reset = "\u001B[0m";   // Kode ANSI untuk mereset warna

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
                    manageInventory();
                    break;
                case "deck":
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
            Plant plant1 = new Plant("Peashooter", 100, 25, 4, 100, -1, 10, false);
            Plant plant2 = new Plant("Sunflower", 50, 0, 0, 50, 0, 10, false);
            Plant plant3 = new Plant("Wall-nut", 1000, 0, 0, 50, 0, 20, false);
            Plant plant4 = new Plant("Snow Pea", 100, 25, 4, 175, -1, 10,false);
            Plant plant5 = new Plant("Chomper", 1000, 1000, 0, 150, 0, 10,false);
            Plant plant6 = new Plant("Squash", 100, 5000, 0, 50, 1, 20, false);
            Plant plant7 = new Plant("Jalapeno", 100, 0, 0, 150, 0, 20, false);
            Plant plant8 = new Plant("Tall-nut", 2000, 0, 0, 100, 0, 20, false);
            Plant plant9 = new Plant("Lilypad", 100, 0, 0, 25, 0, 10, true);
            Plant plant10 = new Plant("Sun-Shroom", 100, 0, 0, 15, 0, 5, false);

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
        System.out.println(green + "========================GAME MENU=========================" + reset);
        System.out.println(yellow + "|           START            |          INVENTORY        |");
        System.out.println("|           DECK             |            HELP           |");
        System.out.println("|           EXIT             |                           |" + reset);
        System.out.println(green + "==========================================================" + reset);
        System.out.print("Enter your choice: ");
    }

    public static void manageInventory() {
        while (true) {
            System.out.println(green + "======================INVENTORY MENU======================" + reset);
            System.out.println(yellow + "|      DISPLAY INVENTORY     |      SWAP INVENTORY       |");
            System.out.println("|            BACK            |                           |" + reset);
            System.out.println(green + "==========================================================" + reset); 

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
            System.out.println(green + "========================DECK MENU=========================" + reset);
            System.out.println(yellow + "|          SWAP DECK         |        REMOVE DECK        |");
            System.out.println("|          ADD PLANT         |        DISPLAY DECK       |"); 
            System.out.println("|            BACK            |                           |" + reset);
            System.out.println(green + "==========================================================" + reset);
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

                if (command.equalsIgnoreCase("play")) {
                    Map map = new Map(6, 11);
                    map.initiateMap();
                }
            }
        }
    }
}
