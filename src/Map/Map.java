package Map;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Tanaman.*;  // Assuming specific plant classes are under this package
import Zombie.*; // Assuming specific zombie classes are under this package

class Tile {
    private List<Plant> plants = new ArrayList<>();
    private List<Zombie> zombies = new ArrayList<>();
    private boolean isWater;
    private boolean isSpawnArea;

    public Tile(boolean isWater, boolean isSpawnArea) {
        this.isWater = isWater;
        this.isSpawnArea = isSpawnArea;
    }

    public void addPlant(Plant plant) {
        if ((isWater && plant.getIsAquatic()) || !isWater && !isSpawnArea) {
            plants.add(plant);
            System.out.println("Added " + plant.getName() + " to " + (isWater ? "water" : "land") + " tile.");
        } else {
            System.out.println("Cannot place " + plant.getName() + " on this type of tile."); // Nanti dibuat exception
        }
    }

    public void addZombie(Zombie zombie) {
        if (isSpawnArea) {
            zombies.add(zombie);
            System.out.println("Zombie spawned on tile.");
        } else {
            System.out.println("Cannot spawn zombie here.");
        }
    }

    public boolean isWater() {
        return isWater;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }
    public List<Plant> getPlants(){
        return plants;
    }
}

public class Map {
    private Tile[][] tiles;
    private Random random = new Random();
    private List<Class<? extends Zombie>> zombieTypes;

    public Map(int rows, int cols) {
        tiles = new Tile[6][11];
        setupTiles();
        initializeZombieTypes();
    }
    private void initializeZombieTypes() {
        zombieTypes = new ArrayList<>();
        zombieTypes.add(BucketHead.class);
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
    private void setupTiles() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                boolean isWater = (i == 2 || i == 3);
                boolean isSpawnArea = (j == tiles[i].length - 1);
                tiles[i][j] = new Tile(isWater, isSpawnArea);
            }
        }
    }

    public void addPlant(Plant plant, int i, int j) {
        if ((i == 0 || i == 1 || i == 4 || i == 5) && (j >= 1 && j <= 9)) {
            tiles[i][j].addPlant(plant);
        } else {
            System.out.println("Invalid position for plant placement.");
        }
    }
    public void spawnZombies() {
        int spawnColumn = tiles[0].length - 1;  // Zombies spawn in the last column
        for (int i = 0; i < tiles.length; i++) {
            if (random.nextDouble() < 0.3) {  // 30% chance to spawn a zombie
                Class<? extends Zombie> zombieClass = zombieTypes.get(random.nextInt(zombieTypes.size()));
                try {
                    // Example string parameter "ZombieType" can be replaced with actual useful data
                    String zombieType = zombieClass.getSimpleName(); // Using the class name as the type
                    Zombie zombie = zombieClass.getDeclaredConstructor(String.class).newInstance(zombieType);
                    tiles[i][spawnColumn].addZombie(zombie);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    System.out.println("Failed to instantiate zombie: " + e.getMessage());
                }
            }
        }
    }
    public void moveZombies() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                boolean zombieReachedBase = false; // Flag to check if zombies reach the base
    
                // Iterate over each row
                for (int i = 0; i < tiles.length; i++) {
                    // Iterate from the second column to the first
                    for (int j = 1; j < tiles[i].length; j++) {
                        List<Zombie> zombiesToMove = new ArrayList<>(tiles[i][j].getZombies());
                        if (!zombiesToMove.isEmpty()) {
                            // Move all zombies from current tile to the left tile
                            tiles[i][j - 1].getZombies().addAll(zombiesToMove);
                            tiles[i][j].getZombies().clear(); // Clear the current tile of zombies
                        }
                    }
    
                    // Check if zombies have reached the base (column 0)
                    if (!tiles[i][0].getZombies().isEmpty()) {
                        zombieReachedBase = true;
                    }
                }
    
                System.out.println("Zombie moved.");
                displayMap(); // Display the map after moving zombies
    
                // If zombies reached the base column, stop the timer and handle game over logic
                if (zombieReachedBase) {
                    System.out.println("Zombie have reached the base!");
                    timer.cancel(); // Stop the timer to halt further moves
                    // Implement any additional game over logic here
                }
            }
        };
    
        timer.schedule(task, 5000, 5000); // Schedule the task to run every 5 seconds
    }

    public void attack() {
        for (int i = 0; i < tiles.length; i++) { // iterate over each row
            List<Plant> plantsInRow = new ArrayList<>();
            List<Zombie> zombiesInRow = new ArrayList<>();
    
            for (int j = 0; j < tiles[i].length; j++) { // iterate over each column in the row
                plantsInRow.addAll(tiles[i][j].getPlants());
                zombiesInRow.addAll(tiles[i][j].getZombies());
            }
    
            // If there are both plants and zombies in the row, they attack each other
            if (!plantsInRow.isEmpty() && !zombiesInRow.isEmpty()) {
                for (Plant plant : plantsInRow) {
                    for (Zombie zombie : zombiesInRow) {
                        plant.attack(zombie); // plant attacks zombie
                        zombie.attack(plant); // zombie attacks plant
                    }
                }
            }
        }
    }
    public void displayMap() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                String tileContent = "   ";  // Default empty tile
                if (!tiles[i][j].getZombies().isEmpty()) {
                    Zombie firstZombie = tiles[i][j].getZombies().get(0);
                    int zombieCount = tiles[i][j].getZombies().size();
                    tileContent = getZombieSymbol(firstZombie);  // Get symbol
                    if (zombieCount > 1) {
                        tileContent += zombieCount;  // Append count if more than one zombie
                    }
                } else if (!tiles[i][j].getPlants().isEmpty()) {
                    Plant firstPlant = tiles[i][j].getPlants().get(0);
                    int plantCount = tiles[i][j].getPlants().size();
                    tileContent = getPlantSymbol(firstPlant);  // Get symbol
                    if (plantCount > 1) {
                        tileContent += plantCount;  // Append count if more than one plant
                    }
                }
                System.out.print(String.format("[%3s]", tileContent));  // Padded for alignment
            }
            System.out.println();
        }
    }
    
    // Helper method to return the symbol for a given plant instance
    private String getPlantSymbol(Plant plant) {
        if (plant instanceof Peashooter) {
            return "P";
        } else if (plant instanceof Sunflower) {
            return "S";
        } else if (plant instanceof Chomper) {
            return "M";
        } else if (plant instanceof SnowPea) {
            return "R";
        } else if (plant instanceof Squash) {
            return "Q";
        } else if (plant instanceof SunShroom) {
            return "N";
        } else if (plant instanceof TallNut) {
            return "T";
        } else if (plant instanceof Jalapeno) {
            return "J";
        } else if (plant instanceof Lilypad) {
            return "L";
        } else if (plant instanceof WallNut) {
            return "W";
        }
        return null;
    }
    private String getZombieSymbol(Zombie zombie) {
        if (zombie instanceof BucketHead) {
            return "B";
        } else if (zombie instanceof ConeHeadZombie) {
            return "C";
        } else if (zombie instanceof DolphinRiderZombie) {
            return "D";
        } else if (zombie instanceof DuckyTubeZombie) {
            return "DT";
        }else if (zombie instanceof FootballZombie) {
            return "F";
        }else if (zombie instanceof NewsPaperZombie) {
            return "N";
        }else if (zombie instanceof NormalZombie) {
            return "Z";
        }else if (zombie instanceof PoleVaultingZombie) {
            return "PV";
        }else if (zombie instanceof ScreenDoorZombie) {
            return "S";
        }else if (zombie instanceof YetiZombie) {
            return "Y";
        }
        return null;
    }
    public static void main(String[] args) {
        Map map = new Map(6, 11);
        map.addPlant(new Peashooter(null, 0, 0, 0, 0, 0, 0), 1, 3);
        map.addPlant(new Peashooter(null, 0, 0, 0, 0, 0, 0), 2, 0);
        map.addPlant(new Peashooter(null, 0, 0, 0, 0, 0, 0), 3, 2);
        map.spawnZombies();
        for(int i = 1; i < 2; i++){
            map.moveZombies();
            map.displayMap();
        }
        
    }
}
