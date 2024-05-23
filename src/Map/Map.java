package Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;

import Sun.*;
import java.util.HashMap;
import Tanaman.*;
import Zombie.*;
import Main.Deck;
import java.util.Iterator;

public class Map {
    private Tile[][] tiles;
    private Random random = new Random();
    private List<Class<? extends Zombie>> zombieTypes;
    private boolean continueSpawning = true;
    private Deck deck;
    private java.util.Map<Plant, Zombie> plantTargetMap = new HashMap<>();
    static String red = "\u001B[31m";    // Kode ANSI untuk warna merah
    static String green = "\033[32m";  // Kode ANSI untuk warna hijau
    static String blue = "\033[34m";   // Kode ANSI untuk warna biru
    static String yellow = "\033[33m";   // Kode ANSI untuk warna kuning
    static String reset = "\u001B[0m";
    private long startTime;
    private boolean gameStarted = false;
   // Kode ANSI untuk mereset warna
    // private Runnable zombieReachedBaseListener;

    public Map(int x, int y, Deck deck) {
        tiles = new Tile[6][11];
        this.deck = deck;
        setupTiles();
        initializeZombieTypes();
        initializeTiles();
    }

    private void initializeTiles() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                boolean isWater = (i == 2 || i == 3);
                boolean isSpawnArea = (j == tiles[i].length - 1); // Menyesuaikan kondisi untuk area spawn
                tiles[i][j] = new Tile(isWater, isSpawnArea);
            }
        }
    }
    public void startGame(){
        startTime = System.currentTimeMillis();
        gameStarted = true;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public boolean getAdaPlant(Tile[][] tiles, int i, int j) {
        return !tiles[i][j].getPlants().isEmpty();
    }

    public boolean getAdaZombie(Tile[][] tiles, int i, int j) {
        return !tiles[i][j].getZombies().isEmpty();
    }

    public void zombieAttacking() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (!tiles[i][j].getZombies().isEmpty()) {
                    Zombie zombie = tiles[i][j].getZombies().get(0);
                    if (j == 0) {
                        continueSpawning = false;
                        return;
                    }
                    if (j > 0 && !tiles[i][j - 1].getPlants().isEmpty()) {
                        Plant plant = tiles[i][j - 1].getPlants().get(0);
                        if (zombie instanceof PoleVaultingZombie && !((PoleVaultingZombie) zombie).hasJumped()) {
                            ((PoleVaultingZombie) zombie).jumpTile(tiles, i, j);
                        } 
                        else {
                            plant.setHealth(plant.getHealth() - zombie.getAttackDamage());

                            if (plant.getHealth() <= 0){
                                tiles[i][j - 1].getPlants().clear();
                                System.out.println(zombie.getName() + " attacked " + plant.getName() + " on tile [" + i + "][" + (j - 1) + "]");
                                System.out.println(plant.getName() + " on tile [" + i + "][" + (j - 1) + "] has been destroyed.");
                            }
                        }
                    }
                }
            }
        }
    }

    public void processZombies() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = tiles[i].length - 1; j >= 0; j--) {  // Iterate from right to left
                List<Zombie> zombies = new ArrayList<>(tiles[i][j].getZombies());
                for (Zombie zombie : zombies) {
                    if (zombie instanceof PoleVaultingZombie && !((PoleVaultingZombie) zombie).hasJumped()) {
                        ((PoleVaultingZombie) zombie).jumpTile(tiles, i, j);
                        addZombie(zombie, i, j-2);
                    }
                }
            }
        }
    
        // Handle other zombie behaviors after jumping
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                List<Plant> plants = tiles[i][j].getPlants();
                for (Plant plant : plants) {
                    if (plant instanceof Chomper) {
                        ((Chomper) plant).instantKillZombie(tiles, i, j);
                    }
                }
            }
        }
    
        // Finally, handle the zombie attacks
        zombieAttacking();
    }

    public void plantAttacking() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                List<Plant> plants = new ArrayList<>(tiles[i][j].getPlants());
                Iterator<Plant> plantIterator = plants.iterator();
    
                while (plantIterator.hasNext()) {
                    Plant plant = plantIterator.next();
                    if (plant instanceof Lilypad) {
                        Plant plantOnTop = ((Lilypad) plant).getPlantOnTop();
                        if (plantOnTop != null) {
                            plant = plantOnTop;
                        } else {
                            continue;
                        }
                    }
                    if (plant instanceof Peashooter) {
                        Zombie targetZombie = plantTargetMap.get(plant);
                        boolean targetFound = false;
                        for (int col = j; col < tiles[i].length; col++) {
                            if (!tiles[i][col].getZombies().isEmpty()) {
                                if (targetZombie == null || targetZombie.getHealth() <= 0 || !tiles[i][col].getZombies().contains(targetZombie)) {
                                    targetZombie = tiles[i][col].getZombies().get(0);
                                    plantTargetMap.put(plant, targetZombie);
                                }
                                targetFound = true;
                                break;
                            }
                        }
    
                        if (!targetFound) {
                            plantTargetMap.remove(plant);
                            continue;
                        }
                        targetZombie.setHealth(targetZombie.getHealth() - plant.getAttackDamage());
                        if (targetZombie.getHealth() <= 0) {
                            for (int col = j; col < tiles[i].length; col++) {
                                if (tiles[i][col].getZombies().contains(targetZombie)) {
                                    tiles[i][col].getZombies().remove(targetZombie);
                                    plantTargetMap.remove(plant); 
                                    break;
                                }
                            }
                        }
                    }
                    if (plant instanceof Squash) {
                        ((Squash) plant).attackZombie(tiles, i, j);
                    }
                    if (plant instanceof Jalapeno) {
                        ((Jalapeno) plant).attackRow(tiles, i, j);
                        plantIterator.remove();
                    }
                    if (plant instanceof Chomper) {
                        ((Chomper) plant).instantKillZombie(tiles, i, j);
                    }
                    if (plant instanceof SnowPea) {
                        ((SnowPea) plant).attackZombie(tiles, i, j);
                    }
                }
            }
        }
    }
    
    public void setupTiles() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                boolean isWater = (i == 2 || i == 3);
                boolean isSpawnArea = (j == tiles[i].length - 1);
                tiles[i][j] = new Tile(isWater, isSpawnArea);
            }
        }
    }
    
    public synchronized void addPlant(Plant plant, int i, int j) {
        if (deck.isPlantInDeck(plant.getName())) {
            if (isValidPosition(i, j)) {
                if (canPlacePlant(plant, i, j)) {
                    tiles[i][j].addPlant(plant);
                    System.out.println(plant.getName() + " berhasil diplant di [" + i + "][" + j + "]");
                } else {
                    System.out.println("Waduh gak bisa diplant di situ, coba tempat lainn!!");
                }
            } else {
                System.out.println("Waduh gak bisa diplant di situ, coba tempat lainn!!");
            }
        } else {
            System.out.println("Tanaman " + plant.getName() + " tidak ada di deck!");
        }
    }

    public synchronized void addZombie(Zombie zombie, int i, int j){
        if (isValidPosition(i, j)) {
            tiles[i][j].addZombie(zombie);
        } else {
            System.out.println("Invalid position for Zombie.");
        }
    }

    private boolean isValidPosition(int i, int j) {
        return (i >= 0 && i < tiles.length) && (j >= 0 && j < tiles[i].length);
    }

    private boolean canPlacePlant(Plant plant, int i, int j) {
        if (tiles[i][j].isWater() && plant.isLilypad()) {
            return true;
        } else if (!tiles[i][j].isWater() && !plant.isLilypad() && !getAdaPlant(tiles, i, j)) {
            return true;
        } else if (tiles[i][j].isWater() && tiles[i][j].hasLilypad() && !plant.isLilypad()) {
            return true;
        }
        return false;
    }

    public void placeLilypad(Lilypad lilypad, int i, int j) {
        if (isValidPosition(i, j)) {
            tiles[i][j].placeLilypad(lilypad);
        } else {
            System.out.println("Invalid position for Lilypad.");
        }
    }

    public void updateTileNameFromLilypad(Tile tile) {
        if (tile.hasLilypad() && tile.getLilypad().getPlantOnTop() != null) {
            Plant plantOnTop = tile.getLilypad().getPlantOnTop();
            int totalHealth = tile.getLilypad().totalHealth();
            System.out.println("[" + getPlantSymbol(plantOnTop) + ": " + totalHealth + "] Total Health: " + totalHealth);
        }
    }
    

    public void spawnZombies() {
        Thread spawnThread = new Thread(() -> {
            int elapsedTime = 0;  // Track elapsed time in seconds
    
            while (!gameOver()) {
                if (elapsedTime >= 20 && elapsedTime <= 160 && getZombieCount() < 10) {
                    int spawnColumn = tiles[0].length - 1;
    
                    for (int i = 0; i < tiles.length; i++) {
                        if (random.nextDouble() < 0.3) {
                            Class<? extends Zombie> zombieClass = zombieTypes.get(random.nextInt(zombieTypes.size()));
                            String zombieType = zombieClass.getSimpleName();
    
                            if ((zombieType.equals("DuckyTubeZombie") || zombieType.equals("DolphinRiderZombie")) && !tiles[i][spawnColumn].isWater()) {
                                continue;
                            } else if (!(zombieType.equals("DuckyTubeZombie") || zombieType.equals("DolphinRiderZombie")) && tiles[i][spawnColumn].isWater()) {
                                continue;
                            }
    
                            Zombie zombie = createZombie(zombieClass);
                            tiles[i][spawnColumn].addZombie(zombie);
    
                            // Attack handling
                            zombieAttacking();
                            plantAttacking();
                        }
                    }
                }
    
                try {
                    Thread.sleep(3000); // Wait for 3 seconds before the next check and spawn cycle
                    elapsedTime += 3;  // Increment the elapsed time by 3 seconds
                } catch (InterruptedException e) {
                    System.out.println("Zombie spawning thread was interrupted.");
                    Thread.currentThread().interrupt(); // Properly handle thread interruption
                    return;
                }
            }
            System.out.println("Game over condition met, stopping zombie spawning.");
        });
    
        spawnThread.start();
    }
    public boolean zombiesReachedBase() {
        for (int i = 0; i < tiles.length; i++) {
            if (!tiles[i][0].getZombies().isEmpty()) {
                return true;  // Zombies have reached the base
            }
        }
        return false;  // No zombies at the base
    }
    public boolean gameOver(){
        if (zombiesReachedBase()){
            return true;
        }
        if(gameStarted && (System.currentTimeMillis()- startTime > 160000)){
            if (getZombieCount()==0){
                return true;
            }
        }
        return false;
    }
    public int getZombieCount() {
        int count = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                count += tiles[i][j].getZombies().size();
            }
        }
        return count;
    }
    public Zombie createZombie(Class<? extends Zombie> zombieClass) {
        try {
            return zombieClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initializeZombieTypes() {
        zombieTypes = new ArrayList<>();
        zombieTypes.add(BucketHeadZombie.class);
        zombieTypes.add(ConeHeadZombie.class);
        zombieTypes.add(DolphinRiderZombie.class);
        zombieTypes.add(DuckyTubeZombie.class);
        zombieTypes.add(FootballZombie.class);
        zombieTypes.add(NewsPaperZombie.class);
        zombieTypes.add(NormalZombie.class);
        zombieTypes.add(PoleVaultingZombie.class);
        zombieTypes.add(ScreenDoorZombie.class);
        zombieTypes.add(YetiZombie.class);
    }

    public Zombie createZombie(String type) {
        switch (type) {
            case "BucketHead":
                return new BucketHeadZombie();
            case "ConeHeadZombie":
                return new ConeHeadZombie();
            case "DolphinRiderZombie":
                return new DolphinRiderZombie();
            case "DuckyTubeZombie":
                return new DuckyTubeZombie();
            case "FootballZombie":
                return new FootballZombie();
            case "NewsPaperZombie":
                return new NewsPaperZombie();
            case "NormalZombie":
                return new NormalZombie();
            case "PoleVaultingZombie":
                return new PoleVaultingZombie();
            case "ScreenDoorZombie":
                return new ScreenDoorZombie();
            case "YetiZombie":
                return new YetiZombie();
            default:
                throw new IllegalArgumentException("Unknown zombie type: " + type);
        }
    }

    public void moveZombies() {
        new Thread(() -> {
            try {
                int elapsedTime = 0;
                while (!gameOver()) {
                    for (int i = 0; i < tiles.length; i++) {
                        for (int j = 1; j < tiles[i].length; j++) {
                            List<Zombie> zombiesToMove = new ArrayList<>(tiles[i][j].getZombies());
                            if (!zombiesToMove.isEmpty()) {
                                if (!tiles[i][j - 1].getPlants().isEmpty()) {
                                    Plant plant = tiles[i][j - 1].getPlants().get(0);
                                    for (Zombie zombie : zombiesToMove) {
                                        zombieAttacking();
                                        if (plant.getHealth() <= 0) {
                                            tiles[i][j - 1].getZombies().add(zombie);
                                            tiles[i][j].getZombies().remove(zombie);
                                        }
                                    }
                                } else {
                                    tiles[i][j - 1].getZombies().addAll(zombiesToMove);
                                    tiles[i][j].getZombies().clear();
                                }
                            }
                        }
                    }
                    
                    plantAttacking();
                    displayMap();  
                    Thread.sleep(10000);  
                    elapsedTime += 1;  
                }
            } catch (InterruptedException e) {
                System.out.println("Zombie moving thread was interrupted.");
                Thread.currentThread().interrupt(); 
            }
            System.out.println("GAME OVER! ");
        }).start();
    }

    public void displayMap() {
        System.out.println();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                String tileContent = "   ";
                if (!tiles[i][j].getZombies().isEmpty()) {
                    Zombie firstZombie = tiles[i][j].getZombies().get(0);
                    tileContent = getZombieSymbol(firstZombie);
                } else if (!tiles[i][j].getPlants().isEmpty()) {
                    Plant firstPlant = tiles[i][j].getPlants().get(0);
                    tileContent = getPlantSymbol(firstPlant);
                }
    
                String color;
                if (j == 0 || j == 10) {
                    color = red;
                } else if (i == 0 || i == 1 || i == 4 || i == 5) {
                    color = green;
                } else if (i == 2 || i == 3) {
                    color = blue;
                } else {
                    color = reset;
                }
    
                System.out.print(color + String.format("[ %8s ]", tileContent) + reset);
            }
            System.out.println();
        }
    }
    
    private String getPlantSymbol(Plant plant) {
        while (plant.getHealth() > 0){
            if (plant instanceof Peashooter) {
                return "PS" + ": " + plant.getHealth();
            } else if (plant instanceof Sunflower) {
                return "SF" + ": " + plant.getHealth();
            } else if (plant instanceof Chomper) {
                return "CH" + ": " + plant.getHealth();
            } else if (plant instanceof SnowPea) {
                return "SP" + ": " + plant.getHealth();
            } else if (plant instanceof Squash) {
                return "SQ" + ": " + plant.getHealth();
            } else if (plant instanceof TwinSunflower) {
                return "TS" + ": " + plant.getHealth();
            } else if (plant instanceof TallNut) {
                return "TN" + ": " + plant.getHealth();
            } else if (plant instanceof Jalapeno) {
                return "JP" + ": " + plant.getHealth();
            } else if (plant instanceof Lilypad) {
                return "LL" + ": " + plant.getHealth();
            } else if (plant instanceof WallNut) {
                return "WN" + ": " + plant.getHealth();
            }
        }

        if (plant.getHealth() <= 0) {
            return "   ";
        }
        
        return null;
    }

    private String getZombieSymbol(Zombie zombie) {
        if (zombie instanceof BucketHeadZombie) {
            return "BH" + ": " + zombie.getHealth();
        } else if (zombie instanceof ConeHeadZombie) {
            return "CZ" + ": " + zombie.getHealth(); 
        } else if (zombie instanceof DolphinRiderZombie) {
            return "DR" + ": " + zombie.getHealth();
        } else if (zombie instanceof DuckyTubeZombie) {
            return "DT" + ": " + zombie.getHealth();
        } else if (zombie instanceof FootballZombie) {
            return "FB" + ": " + zombie.getHealth();
        } else if (zombie instanceof NewsPaperZombie) {
            return "NP" + ": " + zombie.getHealth();
        } else if (zombie instanceof NormalZombie) {
            return "Z" + ": " + zombie.getHealth();
        } else if (zombie instanceof PoleVaultingZombie) {
            return "PV" + ": " + zombie.getHealth();
        } else if (zombie instanceof ScreenDoorZombie) {
            return "SD" + ": " + zombie.getHealth();
        } else if (zombie instanceof YetiZombie) {
            return "YT" + ": " + zombie.getHealth(); 
        }
        return null;
    }
    
    public void initiateMap(){
        Map map = new Map(6, 11, deck);
        Sun sun = new Sun(0);
        sun.startProducingSun();
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (!gameOver()) {
                System.out.println(yellow + "============================== DECK ================================" + reset);
                deck.displayDeck();
                System.out.println(yellow + "====================================================================" + reset);
                System.out.println("Enter plant type (PS, SF, CH, SP, SQ, SS, TN, JP, LL, WN) and coordinates (i, j): ");
                
                String plantType = scanner.next().toUpperCase(); // Convert to uppercase for consistency
                
                // Check if the plant type is in the deck
                if (!deck.isPlantMatchDeck(plantType)) {
                    System.out.println("Invalid plant type. Please choose a valid plant type from the deck.");
                    continue;
                }
                
                int i = scanner.nextInt();
                int j = scanner.nextInt();
    
                // Create plant based on the entered plant code
                Plant plant = null;
                switch (plantType) {
                    case "PS":
                        plant = new Peashooter(null, 0, 100, 25, 0, 0, 0);
                        break;
                    case "SF":
                        plant = new Sunflower(null, 0, 100, 25, 0, 0, 0);
                        break;
                    case "CH":
                        plant = new Chomper(null, 0, 100, 25, 0, 0, 0);
                        break;
                    case "SP":
                        plant = new SnowPea(null, 0, 100, 25, 0, 0, 0);
                        break;
                    case "SQ":
                        plant = new Squash(null, 0, 100, 25, 0, 0, 0);
                        break;
                    case "TS":
                        plant = new TwinSunflower(null, 0, 100, 25, 0, 0, 0);
                        break;
                    case "TN":
                        plant = new TallNut(null, 0, 100, 25, 0, 0, 0);
                        break;
                    case "JP":
                        plant = new Jalapeno(null, 0, 100, 25, 0, 0, 0);
                        break;
                    case "LL":
                        plant = new Lilypad(null, 0, 100, 25, 0, 0, 0);
                        break;
                    case "WN":
                        plant = new WallNut(null, 100, 100, 25, 0, 0);
                        break;
                    default:
                        System.out.println("Invalid plant type.");
                        continue;
                }
                map.addPlant(plant, i, j);
            }
        });
    
        inputThread.start();
        map.spawnZombies();
        map.moveZombies();
    }

    private boolean isPlantCodeValid(String plantCode) {
        return deck.isPlantInDeck(getPlantNameFromCode(plantCode));
    }
    
    private String getPlantNameFromCode(String plantCode) {
        switch (plantCode) {
            case "PS":
                return "Peashooter";
            case "SF":
                return "Sunflower";
            case "CH":
                return "Chomper";
            case "SP":
                return "Snow Pea";
            case "SQ":
                return "Squash";
            case "TS":
                return "Twin Sunflower";
            case "TN":
                return "Tall Nut";
            case "JP":
                return "Jalapeno";
            case "LL":
                return "Lilypad";
            case "WN":
                return "Wall-nut";
            default:
                return "";
        }
    }
}
