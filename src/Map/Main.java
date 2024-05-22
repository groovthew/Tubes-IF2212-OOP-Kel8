package Map;

import java.util.Random;
import java.util.Scanner;

import Exceptions.CantBePlantedException;
import Tanaman.*;
import Zombie.*;
import Main.Deck;
import Main.Inventory;
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

    public static void main(String[] args) {
        Menu printer = new Menu();
        printer.openingAct();
        initializeInventory();

        while (true) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "1":
                    startGame(scanner);
                    break;
                case "4":
                    manageInventory();
                    break;
                case "2":
                    manageDeck();
                    if (deck.getDeck().size() >= Deck.MAX_PLANTS) {
                        System.out.println(red + "Deck telah terisi penuh!" + reset);
                    }
                    break;
                case "5":
                    // Implement help functionality
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
        System.out.println(yellow + "|             1. START             |       4. INVENTORY        |");
        System.out.println("|             2. DECK              |          5. HELP          |");
        System.out.println("|             3. EXIT              |                           |" + reset);
        System.out.println(green + "================================================================" + reset);
        System.out.print(blue + "Enter your choice: " + reset);
    }

    public static void manageInventory() {
        while (true) {
            System.out.println(green + "=========================INVENTORY MENU=========================" + reset);
            System.out.println(yellow + "|        1. DISPLAY INVENTORY      |      2. SWAP INVENTORY    |");
            System.out.println("|              3. BACK             |                           |" + reset);
            System.out.println(green + "================================================================" + reset); 

            System.out.print("Masukkan Pilihanmu: ");
            String inventoryChoice = scanner.nextLine();

        switch (inventoryChoice.toLowerCase()) {
            case "1":
                // Menampilkan isi inventory
                inventory.displayInventory();
                break;
            case "2":
                System.out.println("Enter the positions of the plants to swap (separated by space): ");
                int position1 = scanner.nextInt();
                int position2 = scanner.nextInt();
                scanner.nextLine(); 
                inventory.swapPlants(position1 - 1, position2 - 1);
                break;
            case "3":
                return;
            default:
                System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void manageDeck() {
        while (true) {
            System.out.println(green + "===========================DECK MENU============================" + reset);
            System.out.println(yellow + "|            1. SWAP DECK          |          3. REMOVE DECK   |");
            System.out.println("|            2. ADD PLANT          |          4. DISPLAY DECK  |"); 
            System.out.println("|              5. BACK             |                           |" + reset);
            System.out.println(green + "================================================================" + reset);
            System.out.print("Pilih menu yang diinginkan: ");
            String deckChoice = scanner.nextLine();
    
            switch (deckChoice.toLowerCase()) {
                case "1":
                if (!deck.isDeckEmpty()) {
                    System.out.println("Enter the positions of the plants to swap (separated by space): ");
                    int position1 = scanner.nextInt();
                    int position2 = scanner.nextInt();
                    scanner.nextLine(); 
                    
    
                    deck.swapDeck(position1 - 1, position2 - 1);
                } else {
                    System.out.println("Deck kosong. Tidak ada tanaman yang bisa ditukar.");
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
                case "4":
                    System.out.println("Deck: ");
                    deck.displayDeck();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }    

    public static void startGame(Scanner scanner) {
        // Random random = new Random();
        // Map gameMap = new Map(6, 11);
        // Tile tile = new Tile(false, false);
        System.out.println(blue + "ENTER COMMAND" + reset);
            System.out.println(green + "==============================================" + reset);
            System.out.println(yellow + "|  1. PLAY  |   2. DISPLAY   |  3. BACK     |" + reset);
            System.out.println( green+ "==============================================" + reset);

            boolean gameRunning = true;
            while (gameRunning) {
                System.out.print("Please enter a command: ");
                String command = scanner.nextLine().trim();  // Get user input
        
                if (command.equalsIgnoreCase("1")) {
                    Map map = new Map(6, 11);
                    map.plantInput();
                    map.spawnZombies();
                    map.moveZombies();
                    SunManager sunManager = new SunManager();  // Use singleton instance
                    Sun sun = new Sun(0);
                    //sun.startProducingSun(map);
                    sun.setSunListener(sunManager);
                    sunManager.addProducer(sun);
        
                    // Run a loop to keep checking the game state
                    while (!map.gameOver()) {
                        try {
                            Thread.sleep(1000);  // Check the game state every second
                        } catch (InterruptedException e) {
                            System.out.println("Main game loop interrupted.");
                            break;
                        }
                    }
                    System.out.println("Game Over. Press any key to return to main menu.");
                    scanner.nextLine();  // Wait for user input to return to the main menu
                    gameRunning = false;  // Optionally set to false if you want to exit the game after one round
                } else if (command.equalsIgnoreCase("2")) {
                    // Optionally display the map or other game information
                    System.out.println("Displaying game information...");
                    // gameMap.displayMap(); // Uncomment if you have a method to display the game map or state
                } else if (command.equalsIgnoreCase("3")) {
                    System.out.println("Exiting game...");
                    gameRunning = false;
                } else {
                    System.out.println("Invalid command, please try again.");
                }
            }
    }
}
