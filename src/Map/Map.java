package Map;

// package Map;
// import java.util.ArrayList;
// import java.util.
// import java.util.Random;

// import Tanaman.Peashooter;
// import Tanaman.Plant;
// import Zombie.Zombie;

// public class Map {
//     private int[][] gameMap;
//     private List<Zombie> zombies;
//     private List<Plant> plants;

//     public Map(int rows, int cols) {
//         gameMap = new int[rows][cols];
//         zombies = new ArrayList<>();
//         plants = new ArrayList<>();
//     }

//     public void addPlant(Plant plant, int x, int y) {
//         if (plant == null || x < 0 || x >= gameMap.length || y < 0 || y >= gameMap[0].length) {
//             System.out.println("Invalid plant placement.");
//             return;
//         }
//         if (gameMap[x][y] == 0) {
//             plants.add(plant);
//             gameMap[x][y] = 1;  // 1 indicates a plant
//             System.out.println("Plant added at (" + x + ", " + y + ")");
//         } else {
//             System.out.println("Position already occupied.");
//         }
//     }

//     public void addZombie(Zombie zombie) {
//         Random random = new Random();
//         int y = gameMap[0].length - 1; // Rightmost column
//         List<Integer> emptyRows = new ArrayList<>();
//         for (int x = 0; x < gameMap.length; x++) {
//             if (gameMap[x][y] == 0) {
//                 emptyRows.add(x);
//             }
//         }
//         if (!emptyRows.isEmpty()) {
//             int randomRow = emptyRows.get(random.nextInt(emptyRows.size()));
//             zombies.add(zombie);
//             gameMap[randomRow][y] = 2;  // 2 indicates a zombie
//             System.out.println("Zombie added at (" + randomRow + ", " + y + ")");
//         } else {
//             System.out.println("No available rows to add a zombie in the rightmost column.");
//         }
//     }

//     public void displayMap() {
//         System.out.println("Current game map:");
//         for (int[] row : gameMap) {
//             for (int cell : row) {
//                 System.out.print(cell + " ");
//             }
//             System.out.println();
//         }
//     }

//     public static void main(String[] args) {
//         Map map = new Map(6,8);
//         map.addPlant(new Peashooter(null, 0, 0, 0, 0, 0, 0), 1, 1);
//     }
// }
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

    public Map(int rows, int cols) {
        tiles = new Tile[rows][cols];
        setupTiles();
    }

    private void setupTiles() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                boolean isWater = (j == 2 || j == 3);
                boolean isSpawnArea = (j == tiles[i].length - 1);
                tiles[i][j] = new Tile(isWater, isSpawnArea);
            }
        }
    }

    public void addPlant(Plant plant, int x, int y) {
        if (x >= 2 && x < tiles.length && y < tiles[x].length) {
            tiles[x][y].addPlant(plant);
        } else {
            System.out.println("Invalid position for plant placement.");
        }
    }

    public void spawnZombies() {
        for (int i = 0; i < tiles.length; i++) {
            if (random.nextDouble() < 0.3) { // Randomly decide to spawn a zombie
                Zombie zombie = new PoleVaultingZombie(null, i, i, i); // Simplify, usually you would vary type
                tiles[i][tiles[0].length - 1].addZombie(zombie);
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
        // Iterate through each row
        for (int i = 0; i < tiles.length; i++) {
            // Iterate through each column in the row
            for (int j = 0; j < tiles[i].length; j++) {
                // Determine the content of the tile for display based on the presence of zombies or plants
                String tileContent = " "; // Default empty tile
                if (!tiles[i][j].getZombies().isEmpty()) {
                    // Display 'Z' if there are any zombies on the tile
                    Zombie firstZombie = tiles[i][j].getZombies().get(0);
                    int zombieCount = tiles[i][j].getZombies().size();
                    tileContent = getZombieSymbol(firstZombie)+ zombieCount;
                } else if (!tiles[i][j].getPlants().isEmpty()) {
                    // Display the first plant's symbol if there are plants and no zombies on the tile
                    Plant firstPlant = tiles[i][j].getPlants().get(0);
                    tileContent = getPlantSymbol(firstPlant); // A method to get symbol based on plant type
                }
                // Print the content of the tile
                System.out.print(String.format("[%3s]", tileContent)); // Padded for alignment
            }
            // Move to the next line after printing all columns in the current row
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
            return "P";
        }else if (zombie instanceof ScreenDoorZombie) {
            return "S";
        }else if (zombie instanceof YetiZombie) {
            return "Y";
        }
        return null;

    }
    public static void main(String[] args) {
        Map map = new Map(6, 11);
        map.addPlant(new Jalapeno(null, 0, 0, 0, 0, 0, 0), 5, 1);
        map.addPlant(new WallNut(null, 0, 0, 0, 0, 0), 4, 1); // Example to add a Peashooter
        map.spawnZombies();
        for(int i = 0; i < 2; i++){
            map.moveZombies();
            map.displayMap();
        }
        
    }
}