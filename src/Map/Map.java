package Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import Tanaman.*;
import Zombie.*;

public class Map {
    private Tile[][] tiles;
    private Random random = new Random();
    private List<Class<? extends Zombie>> zombieTypes;
    private boolean continueSpawning = true;

    public Map(int x, int y) {
        tiles = new Tile[x][y];
        setupTiles();
        initializeZombieTypes();
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

    public synchronized void addPlant(Plant plant, int i, int j) {
        if ((i == 0 || i == 1 || i == 4 || i == 5) && (j >= 1 && j <= 10)) {
            tiles[i][j].addPlant(plant);
        } else if ((i == 2 || i == 3) && plant instanceof Lilypad) {
            tiles[i][j].addPlant(plant);
        } else {
            System.out.println("Invalid position or plant type for the specified position.");
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
        } else if (zombie instanceof FootballZombie) {
            return "F";
        } else if (zombie instanceof NewsPaperZombie) {
            return "N";
        } else if (zombie instanceof NormalZombie) {
            return "Z";
        } else if (zombie instanceof PoleVaultingZombie) {
            return "PV";
        } else if (zombie instanceof ScreenDoorZombie) {
            return "S";
        } else if (zombie instanceof YetiZombie) {
            return "Y";
        }
        return null;
    }

    public void initiateMap(){
        Map map = new Map(6, 11);

        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Enter plant type (PS, SF, CH, SP, SQ, SS, TN, JP, LL, WN) and coordinates (i, j): ");
                String plantType = scanner.next();
                int i = scanner.nextInt();
                int j = scanner.nextInt();

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
                    case "SS":
                        plant = new SunShroom(null, 0, 100, 25, 0, 0, 0);
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
        map.displayMap();
    }

    public static void main(String[] args) {
        Map map = new Map(6, 11);
        map.initiateMap();
    }
}
