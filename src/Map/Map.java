package Map;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Tanaman.*;  // Assuming specific plant classes are under this package
import Zombie.*; // Assuming specific zombie classes are under this package

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
            if (!(plant instanceof Lilypad)){
                throw new IllegalArgumentException("Cannot place plant on this tile.");
            }
            else{
                tiles[i][j].addPlant(plant);
            }
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
            return "PS";
        } else if (plant instanceof Sunflower) {
            return "SF";
        } else if (plant instanceof Chomper) {
            return "CH";
        } else if (plant instanceof SnowPea) {
            return "SP";
        } else if (plant instanceof Squash) {
            return "SQ";
        } else if (plant instanceof SunShroom) {
            return "SS";
        } else if (plant instanceof TallNut) {
            return "TN";
        } else if (plant instanceof Jalapeno) {
            return "JP";
        } else if (plant instanceof Lilypad) {
            return "LL";
        } else if (plant instanceof WallNut) {
            return "WN";
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
