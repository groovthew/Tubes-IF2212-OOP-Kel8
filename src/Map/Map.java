package Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Tanaman.*;
import Zombie.*;
import Map.Tile;

public class Map {
    private Tile[][] tiles;
    private Random random = new Random();
    private List<Class<? extends Zombie>> zombieTypes;
    private boolean continueSpawning = true;

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
        Timer spawnTimer = new Timer();
        TimerTask spawnTask = new TimerTask() {
            @Override
            public void run() {
                if (!continueSpawning) {
                    System.out.println("Zombies have reached the base.");
                    spawnTimer.cancel();
                    return;
                }
    
                int spawnColumn = tiles[0].length - 1;
                for (int i = 0; i < tiles.length; i++) {
                    if (random.nextDouble() < 0.3) {
                        Class<? extends Zombie> zombieClass = zombieTypes.get(random.nextInt(zombieTypes.size()));
                        String zombieType = zombieClass.getSimpleName();
    
                        if ((zombieType.equals("DuckyTubeZombie") || zombieType.equals("DolphinRiderZombie")) && (i != 2 && i != 3)) {
                            continue;
                        } else if (!(zombieType.equals("DuckyTubeZombie") || zombieType.equals("DolphinRiderZombie")) && (i != 0 && i != 1 && i != 4 && i != 5)) {
                            continue;
                        }
    
                        Zombie zombie = createZombie(zombieType);
                        tiles[i][spawnColumn].addZombie(zombie);
                    }
                }
            }
        };
    
        spawnTimer.schedule(spawnTask, 0, 5000);
    }
    
    
    public Zombie createZombie(String type) {
        switch (type) {
            case "BucketHead":
                return new BucketHead();
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
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                boolean zombieReachedBase = false;
    
                for (int i = 0; i < tiles.length; i++) {
                    if (!tiles[i][0].getZombies().isEmpty()) {
                        zombieReachedBase = true;
                        break;
                    }
    
                    for (int j = 1; j < tiles[i].length; j++) {
                        List<Zombie> zombiesToMove = new ArrayList<>(tiles[i][j].getZombies());
                        if (!zombiesToMove.isEmpty()) {
                            tiles[i][j - 1].getZombies().addAll(zombiesToMove);
                            tiles[i][j].getZombies().clear();
                        }
                    }
                }
    
                if (zombieReachedBase) {
                    System.out.println("Zombie has reached the base!");
                    continueSpawning = false;  
                    timer.cancel(); 
                }
    
                displayMap();
            }
        };
    
        timer.schedule(task, 5000, 5000);
    }
    
    public void attack() {
        for (int i = 0; i < tiles.length; i++) { 
            List<Plant> plantsInRow = new ArrayList<>();
            List<Zombie> zombiesInRow = new ArrayList<>();
    
            for (int j = 0; j < tiles[i].length; j++) { 
                plantsInRow.addAll(tiles[i][j].getPlants());
                zombiesInRow.addAll(tiles[i][j].getZombies());
            }
            if (!plantsInRow.isEmpty() && !zombiesInRow.isEmpty()) {
                for (Plant plant : plantsInRow) {
                    for (Zombie zombie : zombiesInRow) {
                        plant.attack(zombie); 
                        //zombie.attack(plant); 
                    }
                }
            }
        }
    }
    public void displayMap() {
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
                System.out.print(String.format("[%3s]", tileContent)); 
            }
            System.out.println();
        }
    }
    
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
        map.moveZombies();
        map.displayMap();
        
    }
}
