package Map;

import java.util.*;
import Tanaman.*;
import Zombie.*;
import Main.Deck;
import Strategy.AttackStrategy;
import Strategy.PlantAttackStrategy;
import Strategy.ZombieAttackStrategy;
import Sun.Sun;

public class Map {
    private Tile[][] tiles;
    private Random random = new Random();
    private List<Class<? extends Zombie>> zombieTypes;
    private boolean continueSpawning = true;
    private Deck deck;
    private java.util.Map<String, Long> plantCooldowns;
    private java.util.Map<Plant, Zombie> plantTargetMap = new HashMap<>();
    static String red = "\u001B[31m";
    static String green = "\033[32m";
    static String blue = "\033[34m";
    static String yellow = "\033[33m";
    static String reset = "\u001B[0m";
    private long startTime;
    private boolean gameStarted = false;

    public Map(int x, int y, Deck deck) {
        tiles = new Tile[6][11];
        this.deck = deck;
        setupTiles();
        initializeZombieTypes();
        initializeTiles();
        plantCooldowns = new HashMap<>();
    }

    private void initializeTiles() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                boolean isWater = (i == 2 || i == 3);
                boolean isSpawnArea = (j == tiles[i].length - 1);
                tiles[i][j] = new Tile(isWater, isSpawnArea);
            }
        }
    }

    public void startGame() {
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
                    AttackStrategy strategy = new ZombieAttackStrategy(tiles, zombie, i, j);
                    strategy.attack();
                }
            }
        }
    }

    public void plantAttacking() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                List<Plant> plants = new ArrayList<>(tiles[i][j].getPlants());
                for (Plant plant : plants) {
                    AttackStrategy strategy = new PlantAttackStrategy(tiles, plant, i, j);
                    strategy.attack();
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
        long currentTime = System.currentTimeMillis();
        String plantName = plant.getName();

        if (plantCooldowns.containsKey(plantName) && currentTime < plantCooldowns.get(plantName)) {
            System.out.println(plantName + " masih cooldown selama " + ((plantCooldowns.get(plantName) - currentTime) / 1000) + "S.");
            return;
        }

        if (deck.isPlantInDeck(plant.getName())) {
            if (isValidPosition(i, j)) {
                if (canPlacePlant(plant, i, j)) {
                    tiles[i][j].addPlant(plant);
                    System.out.println(plant.getName() + " berhasil diplant di [" + i + "][" + j + "]");
                    plant.startCooldown();
                    plantCooldowns.put(plantName, currentTime + (plant.getCooldown() * 1000));
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

    public synchronized void removePlant(int i, int j) {
        if (!isValidPosition(i, j)) {
            System.out.println("Waduh gak bisa diremove di situ, coba tempat lainn!!");
            return;
        }

        Tile tile = tiles[i][j];
        if (!tile.hasPlant()) {
            System.out.println("No plant to remove at position [" + i + "][" + j + "].");
            return;
        }

        tile.removePlant();
        System.out.println("Plant removed from [" + i +"][" + j + "].");
    }

    private boolean isValidPosition(int i, int j) {
        return (i >= 0 && i < tiles.length) && (j >= 0 && j < tiles[i].length);
    }

    private boolean canPlacePlant(Plant plant, int i, int j) {
        Tile tile = tiles[i][j];

        if (tile.isWater() && plant.isLilypad()) {
            return true;
        }
        else if (!tile.isWater() && !plant.isLilypad() && !getAdaPlant(tiles, i, j)) {
            return true;
        }
        else if (tile.isWater() && tile.hasLilypad() && !tile.getLilypad().hasPlantOnTop() && !plant.isLilypad()) {
            return true;
        }
        else if (tile.isWater() && tile.hasLilypad() && !plant.isLilypad()) {
            return tile.getLilypad().getPlantOnTop() == null;
        }
        else {
            return false;
        }
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
            int elapsedTime = 0;

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
                    Thread.sleep(3000);
                    elapsedTime += 3;
                } catch (InterruptedException e) {
                    System.out.println("Zombie spawning thread was interrupted.");
                    Thread.currentThread().interrupt();
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
                return true;
            }
        }
        return false;
    }

    public boolean gameOver() {
        if (zombiesReachedBase()) {
            return true;
        }
        if (gameStarted && (System.currentTimeMillis() - startTime > 160000)) {
            if (getZombieCount() == 0) {
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
                while (!gameOver()) {
                    for (int i = 0; i < tiles.length; i++) {
                        for (int j = 1; j < tiles[i].length; j++) {
                            List<Zombie> zombiesToMove = new ArrayList<>(tiles[i][j].getZombies());
                            if (!zombiesToMove.isEmpty()) {
                                if (!tiles[i][j - 1].getPlants().isEmpty()) {
                                    Plant plant = tiles[i][j - 1].getPlants().get(0);
                                    for (Zombie zombie : zombiesToMove) {
                                        if (zombie instanceof PoleVaultingZombie && !((PoleVaultingZombie) zombie).hasJumped()) {
                                            ((PoleVaultingZombie) zombie).jumpTile(tiles, i, j);
                                        }
                                        if (zombie instanceof DolphinRiderZombie && !((DolphinRiderZombie) zombie).hasJumped()) {
                                            ((DolphinRiderZombie) zombie).jumpTile(tiles, i, j);
                                        }
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
        if (plant.getHealth() > 0) {
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

    public void initiateMap() {
        startGame();
        Sun sun = new Sun(0);
        sun.startProducingSun();
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (!gameOver()) {
                System.out.println(yellow + "============================== DECK ================================" + reset);
                deck.displayDeck();
                System.out.println(yellow + "====================================================================" + reset);
                System.out.println("Enter plant type (PS, SF, CH, SP, SQ, SS, TN, JP, LL, WN) and coordinates (i, j): ");

                String plantType = scanner.next().toUpperCase();

                if (plantType.equals("RP")) {
                    try {
                        int i = scanner.nextInt();
                        int j = scanner.nextInt();
                        removePlant(i, j);
                    } catch (Exception e) {
                        System.out.println("Waduh gak bisa remove di situ. Pilih koordinat lainn!");
                        scanner.nextLine(); 
                    }
                    continue;
                }
    
                if (!deck.isPlantMatchDeck(plantType)) {
                    System.out.println("Invalid plant type. Please choose a valid plant type from the deck.");
                    continue;
                }

                int i = scanner.nextInt();
                int j = scanner.nextInt();

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
                        plant = new WallNut(plantType, j, j, j, i, j);
                        break;
                    default:
                        System.out.println("Invalid plant type.");
                        continue;
                }
                addPlant(plant, i, j);
            }
        });

        inputThread.start();
        spawnZombies();
        moveZombies();
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
