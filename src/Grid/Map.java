import java.util.ArrayList;
import java.util.List;

import Tanaman.Peashooter;
import Tanaman.Plant;
import Tanaman.Sunflower;
import Zombie.Zombie;



public class Map {
    private List<Plant> plants = new ArrayList<>();
    private List<Zombie> zombies = new ArrayList<>();
    private int currentSuns = 50;
    private int totalGameTime = 0; 
    private int width;
    private int height;

    public Map(int width, int height) {
        this.width = width;
        this.height =  height;
    }

    public boolean addPlant(Plant plant) {
        if (currentSuns >= plant.getCost() && plant.getX() >= 0 && plant.getX() < width && plant.getY() >= 0 && plant.getY() < height) {
            plants.add(plant);
            currentSuns -= plant.getCost();  // Deduct the current suns based on the cost of the plant 
            System.out.println("Plant added at (" + plant.getX() + ", " + plant.getY() + ")");
            return true;
        }
        System.out.println("Not enough suns or invalid position.");
        return false;
    }

    public void removePlant(int x, int y) {
        plants.removeIf(plant -> plant.getX() == x && plant.getY() == y);
        System.out.println("Plant removed from (" + x + ", " + y + ")");
    }

    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    public List<Zombie> getZombies() {
        return zombies;  // Return the list of zombies
    }

    public List<Zombie> getZombiesInColumn(int column) {
        List<Zombie> columnZombies = new ArrayList<>();
        for (Zombie zombie : zombies) {
            if (zombie.getY() == column) {
                columnZombies.add(zombie);
            }
        }
        return columnZombies;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Plant> getAllPlants() {
        return new ArrayList<>(plants);  // Return a copy of the plant list
    }

    public void printMap() {
        // Initialize a grid representation
        String[][] grid = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = "_";  // Empty space
            }
        }

        // Place plants on the grid
        for (Plant plant : plants) {
            String plantSymbol = "P";  // Generic plant symbol
            if (plant instanceof Sunflower) {
                plantSymbol = "S";  // Specific symbol for Sunflowers
            } else if (plant instanceof Peashooter) {
                plantSymbol = "P";  // Specific symbol for Peashooters
            }
            grid[plant.getX()][plant.getY()] = plantSymbol;
        }

        // Place zombies on the grid
        for (Zombie zombie : zombies) {
            grid[zombie.getX()][zombie.getY()] = "Z";  // Zombie symbol
        }

        // Print the grid to console
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(grid[i][j] + "");
            }
            System.out.println();  // New line at the end of each row
        }
        System.out.println("Current Suns: " + currentSuns);  // Display current suns
    }

    public Plant getPlant(int x, int y) {
        for (Plant plant : plants) {
            if (plant.getX() == x && plant.getY() == y) {
                return plant;
            }
        }
        return null;
    }
}


// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;

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
// }

// class Plant {
//     private String name;
//     private int cost;

//     public Plant(String name, int cost) {
//         this.name = name;
//         this.cost = cost;
//     }

//     @Override
//     public String toString() {
//         return "Plant{" +
//                "name='" + name + '\'' +
//                ", cost=" + cost +
//                '}';
//     }
// }

// class Zombie {
//     private String type;
//     private int health;

//     public Zombie(String type, int health) {
//         this.type = type;
//         this.health = health;
//     }

//     @Override
//     public String toString() {
//         return "Zombie{" +
//                "type='" + type + '\'' +
//                ", health=" + health +
//                '}';
//     }
// }