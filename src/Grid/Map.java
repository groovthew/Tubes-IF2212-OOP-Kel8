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
        if (currentSuns >= plant.cost && plant.x >= 0 && plant.x < width && plant.y >= 0 && plant.y < height) {
            plants.add(plant);
            currentSuns -= plant.cost;  // Deduct the current suns based on the cost of the plant 
            System.out.println("Plant added at (" + plant.x + ", " + plant.y + ")");
            return true;
        }
        System.out.println("Not enough suns or invalid position.");
        return false;
    }

    public void removePlant(int x, int y) {
        plants.removeIf(plant -> plant.x == x && plant.y == y);
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
            grid[plant.x][plant.y] = plantSymbol;
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
            if (plant.x == x && plant.y == y) {
                return plant;
            }
        }
        return null;
    }
}
