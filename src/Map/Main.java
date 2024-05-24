package Map;

import java.util.Scanner;
import Exceptions.CantBePlantedException;
import Exceptions.CantSwapDeckException;
import Exceptions.DeckIsEmptyException;
import Exceptions.DeckNotFullException;
import Tanaman.*;
import Main.*;
import Sun.*;

public class Main {
    static String green = "\u001B[32m";  // Kode ANSI untuk warna hijau
    static String red = "\u001B[31m";    // Kode ANSI untuk warna merah
    static String blue = "\u001B[34m";   // Kode ANSI untuk warna biru
    static String yellow = "\033[33m";   // Kode ANSI untuk warna kuning
    static String reset = "\u001B[0m";   // Kode ANSI untuk mereset warna

    private static Scanner scanner = new Scanner(System.in);
    private static Deck deck = new Deck();
    private static Inventory inventory = Inventory.getInstance();

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
                    new ZombiesList();
                    break;
                case "2":
                    new PlantsList();
                    break;
                case "5":
                    Help.displayHelp();
                    break;
                case "3":
                    System.out.println(green + "=================" + reset + yellow + " Exiting game. Goodbye!" + reset + green+ "==================" + reset);
                    scanner.close();
                    System.exit(0);
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
        System.out.println("===========================GAME MENU============================");
        System.out.println(yellow + "|             1. START             |       4. ZOMBIE LIST      |");
        System.out.println("|             2. PLANT LIST        |       5. HELP             |");
        System.out.println("|             3. EXIT              |                           |" + reset);
        System.out.println("================================================================");
        System.out.print(blue + "Enter your choice: " + reset);
    }

    public static void gameMenu() throws DeckIsEmptyException, DeckNotFullException, CantSwapDeckException {
        while (true) {
            System.out.println("=============================GAME MENU==================================");
            System.out.println(yellow + "|   1. DISPLAY DECK    |   2. ADD PLANT TO DECK  |   3. REMOVE DECK    |");
            System.out.println("|   4. SWAP DECK       |   5. DISPLAY INVENTORY  |   6. SWAP INVENTORY |");
            System.out.println("|   7. BACK            |   8. START GAME         |                     |" + reset);
            System.out.println("========================================================================");
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
                    System.out.println("Pilih tanaman yang diinginkan:");
                    int plantIndex = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    try {
                        if (plantIndex < 1 || plantIndex > inventory.getInventory().size()) {
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
                        System.out.println("Pilih tanaman yang ingin dihapus dari deck: ");
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        deck.removePlant(index - 1);
                    } else {
                        System.out.println("Deck kosong. Tidak ada tanaman yang bisa dihapus.");
                    }
                    break;
                case "4":
                    if (!deck.isDeckEmpty()) {
                        System.out.println("Pilih 2 tanaman yang ingin ditukar posisinya: ");
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
                    System.out.println("Pilih 2 tanaman yang ingin ditukar posisinya: ");
                    int pos1 = scanner.nextInt();
                    int pos2 = scanner.nextInt();
                    scanner.nextLine(); 
                    inventory.swapPlants(pos1 - 1, pos2 - 1);
                    break;
                case "7":
                    return;
                default:
                    System.out.println(red + "Indeks tidak valid. Harap lakukan pemilihan ulang." + reset);
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
    public static void initiateMap(Map map) {
        Thread spawnThread = new Thread(map::spawnZombies);
        Thread moveThread = new Thread(map::moveZombies);
    
        spawnThread.start();
        moveThread.start();
    
        Sun sun = new Sun(50);
        map.sunManager.addProducer(sun);
        sun.startProducingSun(map);
    
        while (!map.gameOver()) {
            //map.checkDayNightCycle();
            System.out.println(yellow + "============================== DECK ================================" + reset);
            deck.displayDeck();
            System.out.println(yellow + "====================================================================" + reset);
            System.out.println("Masukkan tipe Plant (PS, SF, CH, SP, SQ, TS, TN, JP, LL, WN) dan kordinatnya (i, j): ");
            System.out.println("Input RP i j untuk menghapus tanaman di posisi i j");
            System.out.println("");
    
            String input = scanner.nextLine().toUpperCase().trim();
            String[] parts = input.split(" ");
            if (input.isEmpty()) {
                continue;
            }
            if (parts.length != 3) {
                System.out.println("Salah format input! Contoh input (PS 1 1).");
                continue;
            }
            String plantType = parts[0];
            int i = 0, j = 0;
            try {
                i = Integer.parseInt(parts[1]);
                j = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                System.out.println(" Koordinat tempat tanam salah! ");
                continue;
            }
            if (plantType.equals("RP")) {
                map.removePlant(i, j);
                continue;
            }
            if (!deck.isPlantMatchDeck(plantType)) {
                System.out.println("Tidak ada tanaman itu di deck! ");
                continue;
            }
            Plant plant = null;
            switch (plantType) {
                case "PS":
                    plant = new Peashooter(null, 100, 25, 4, 100, -1, 10);
                    break;
                case "SF":
                    plant = new Sunflower(null, 100, 0, 0, 50, -1, 10);
                    break;
                case "CH":
                    plant = new Chomper(null, 200, 1000, 0, 150, -1, 20);
                    break;
                case "SP":
                    plant = new SnowPea(null, 100, 25, 4, 175, -1, 10);
                    break;
                case "SQ":
                    plant = new Squash(null, 100, 5000, 0, 50, 1, 20);
                    break;
                case "TS":
                    plant = new TwinSunflower(null, 150, 0, 0, 100, -1, 10);
                    break;
                case "TN":
                    plant = new TallNut(null, 150, 0, 0, 125, -1, 10);
                    break;
                case "JP":
                    plant = new Jalapeno(null, 100, 0, 0, 150, -1, 20);
                    break;
                case "LL":
                    plant = new Lilypad(null, 100, 0, 0, 25, -1, 5);
                    break;
                case "WN":
                    plant = new WallNut(null, 300, 0, 0, 50, -1);
                    break;
                default:
                    System.out.println("Invalid plant type.");
                    continue;
            }
            if (!map.isValidPosition(i, j)) {
                System.out.println("Waduh gabisa nanam di posisi : (" + i + ", " + j + ")");
                continue;
            }
    
            if (!map.canPlacePlant(plant, i, j)) {
                System.out.println("Tidak bisa menanam plant " + plant.getClass().getSimpleName() + " di (" + i + ", " + j + ")");
                continue;
            }
            map.addPlant(plant, i, j);
        }
    
        try {
            spawnThread.join();
            moveThread.join();
        } catch (InterruptedException e) {
            System.out.println("Threads interrupted: " + e.getMessage());
        }
    }
    
    public static void startGame(Scanner scanner) {
        boolean exitGame = false;
        while (!exitGame) {
            Map gameMap = new Map(6, 11, deck);
            System.out.println(blue + "ENTER COMMAND" + reset);
                System.out.println("==============================================");
                System.out.println(yellow + "|  1. PLAY  |   2. DISPLAY   |  3. BACK     |" + reset);
                System.out.println("==============================================");
            boolean gameActive = true;
            while (gameActive && !gameMap.gameOver()) {
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
                        initiateMap(gameMap);
                        gameActive = false;
                    }
                        

                    if (command.equalsIgnoreCase("2")) {
                        gameMap.displayMap();
                        System.out.println("==============================================");
                        System.out.println(yellow + "|                    BACK                    |" + reset);
                        System.out.println("==============================================");
                        
                    }
                    if (command.equalsIgnoreCase("back")){
                        gameActive = false;  // Exit the game loop to return to the main menu
                        exitGame = true;
                    }

                    
                }
            }
            if (gameMap.gameOver()) {
                //System.out.println("Game Over! Total Sun: " + SunManager.getTotalSun());
                System.out.println("Game Over! Balik ke menu atau keluar dari permainan ?");
                System.out.println("1. Menu");
                System.out.println("2. Exit");
    
                boolean validChoice = false;
                while (!validChoice) {
                    String choice = scanner.nextLine();
                    if (choice.equals("1")) {
                        validChoice = true;
                        exitGame = true;      // Exit to the main menu
                    } else if (choice.equals("2")) {
                        System.exit(0);       // Exit the program
                    } else {
                        System.out.println("Invalid choice. Please choose again.");
                    }
                }
            }
        }
    }
}
