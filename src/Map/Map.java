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
        if ((isWater && plant.canPlaceOnWater()) || !isWater && !isSpawnArea) {
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
                Zombie zombie = new NormalZombie(null, i, i, i); // Simplify, usually you would vary type
                tiles[i][tiles[0].length - 1].addZombie(zombie);
            }
        }
    }

    public void moveZombies() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < tiles.length; i++) {
                    for (int j = tiles[i].length - 1; j > 0; j--) {
                        List<Zombie> movingZombies = new ArrayList<>(tiles[i][j].getZombies());
                        for (Zombie zombie : movingZombies) {
                            tiles[i][j].getZombies().remove(zombie);
                            tiles[i][j-1].addZombie(zombie);
                        }
                    }
                }
            }
        }, 5000, 5000); // Move zombies every 5 seconds
    }

    public void displayMap() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                String tileContent = tiles[i][j].getZombies().isEmpty() ? " " : "Z";
                System.out.print("[" + tileContent + "]");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Map map = new Map(11, 6);
        map.addPlant(new Peashooter(), 5, 3); // Example to add a Peashooter
        map.spawnZombies();
        map.displayMap();
    }
}