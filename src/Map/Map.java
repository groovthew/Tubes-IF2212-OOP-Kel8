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
    private boolean adaPlant;
    private boolean adaZombie;
    static String red = "\u001B[31m";    // Kode ANSI untuk warna merah
    static String green = "\033[32m";  // Kode ANSI untuk warna hijau
    static String blue = "\033[34m";   // Kode ANSI untuk warna biru
    static String reset = "\u001B[0m";   // Kode ANSI untuk mereset warna

    public Map(int x, int y) {
        tiles = new Tile[x][y];
        setupTiles();
        initializeZombieTypes();
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

    public void zombieAttacking(){
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (!tiles[i][j].getZombies().isEmpty()) {
                    Zombie zombie = tiles[i][j].getZombies().get(0);
                    if (j == 0) {
                        continueSpawning = false;
                        return;
                    }
                    if (j > 0 && !tiles[i][j - 1].getPlants().isEmpty()) {
                        Plant plant = tiles[i][j - 1].getPlants().get(0);
                        plant.setHealth(plant.getHealth() - zombie.getAttackDamage());
                        if (plant.getHealth() <= 0) {
                            tiles[i][j - 1].getPlants().clear();
                            System.out.println(zombie.getName() + " attacked " + plant.getName() + " on tile [" + i + "][" + (j - 1) + "]");
                            System.out.println(plant.getName() + " on tile [" + i + "][" + (j - 1) + "] has been destroyed.");
                        }
                    }
                }
            }
        }
    }

    public void plantAttacking(){
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (!tiles[i][j].getPlants().isEmpty() && !tiles[i][j].getZombies().isEmpty()) {
                    Plant plant = tiles[i][j].getPlants().get(0);

                    // Menyerang zombie yang paling dekat (di posisi pertama dalam list zombies)
                    Zombie zombie = tiles[i][j].getZombies().get(0);
                    zombie.setHealth(zombie.getHealth() - plant.getAttackDamage());
                    
                    if (zombie.getHealth() <= 0) {
                        tiles[i][j].getZombies().clear();
                        System.out.println(plant.getName() + " attacked " + zombie.getName() + " on tile [" + i + "][" + j + "]" + " for " + plant.getAttackDamage() + " damage.");
                        System.out.println(zombie.getName() + " on tile [" + i + "][" + j + "] has been destroyed.");
                    }
                }
            }
        }
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
            plantAttacking();
            this.getAdaPlant(tiles, i, j);
        } else if ((i == 2 || i == 3) && plant instanceof Lilypad) {
            tiles[i][j].addPlant(plant);
            plantAttacking();
            this.getAdaPlant(tiles, i, j);
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
                    System.out.println("WADUH ZOMBIE DAH SAMPE BASE!");
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
                        zombieAttacking();
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
                for (int j = 1; j < tiles[i].length; j++) {
                    List<Zombie> zombiesToMove = new ArrayList<>(tiles[i][j].getZombies());
                    if (!zombiesToMove.isEmpty()) {
                        if (!tiles[i][j - 1].getPlants().isEmpty()) {
                            Plant plant = tiles[i][j - 1].getPlants().get(0);
                            for (Zombie zombie : zombiesToMove) {
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

                if (!tiles[i][0].getZombies().isEmpty()) {
                    zombieReachedBase = true;
                    break;
                }
            }

            plantAttacking();

            if (zombieReachedBase) {
                System.out.println("NT ZOMBIE DAH SAMPE BASE!");
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
    
                System.out.print(color + String.format("[ %3s ]", tileContent) + reset);
            }
            System.out.println();
        }
    }
    
    private String getPlantSymbol(Plant plant) {
        while (plant.getHealth() > 0){
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
        }

        if (plant.getHealth() <= 0) {
            return "   ";
        }
        
        return null;
    }

    private String getZombieSymbol(Zombie zombie) {
        if (zombie instanceof BucketHead) {
            return "BH";
        } else if (zombie instanceof ConeHeadZombie) {
            return "CH"; 
        } else if (zombie instanceof DolphinRiderZombie) {
            return "DR";
        } else if (zombie instanceof DuckyTubeZombie) {
            return "DT";
        } else if (zombie instanceof FootballZombie) {
            return "FB";
        } else if (zombie instanceof NewsPaperZombie) {
            return "NP";
        } else if (zombie instanceof NormalZombie) {
            return "Z";
        } else if (zombie instanceof PoleVaultingZombie) {
            return "PV";
        } else if (zombie instanceof ScreenDoorZombie) {
            return "SD";
        } else if (zombie instanceof YetiZombie) {
            return "YT"; 
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
}
