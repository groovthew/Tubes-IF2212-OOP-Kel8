package Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Tanaman.Plant;
import Zombie.Zombie;

public class Map {
    private int[][] gameMap;
    private List<Zombie> zombies;
    private List<Plant> plants;

    public Map(int rows, int cols) {
        gameMap = new int[rows][cols];
        zombies = new ArrayList<>();
        plants = new ArrayList<>();
    }

    public void addPlant(Plant plant, int x, int y) {
        if (plant == null || x < 0 || x >= gameMap.length || y < 0 || y >= gameMap[0].length) {
            System.out.println("Invalid plant placement.");
            return;
        }
        if (gameMap[x][y] == 0) {
            plants.add(plant);
            gameMap[x][y] = 1;  // 1 indicates a plant
            System.out.println("Plant added at (" + x + ", " + y + ")");
        } else {
            System.out.println("Position already occupied.");
        }
    }

    public void addZombie(Zombie zombie) {
        Random random = new Random();
        int y = gameMap[0].length - 1; // Rightmost column
        List<Integer> emptyRows = new ArrayList<>();
        for (int x = 0; x < gameMap.length; x++) {
            if (gameMap[x][y] == 0) {
                emptyRows.add(x);
            }
        }
        if (!emptyRows.isEmpty()) {
            int randomRow = emptyRows.get(random.nextInt(emptyRows.size()));
            zombies.add(zombie);
            gameMap[randomRow][y] = 2;  // 2 indicates a zombie
            System.out.println("Zombie added at (" + randomRow + ", " + y + ")");
        } else {
            System.out.println("No available rows to add a zombie in the rightmost column.");
        }
    }

    public void displayMap() {
        System.out.println("Current game map:");
        for (int[] row : gameMap) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}