package Main;
import Zombie.*;

public class ZombiesList {
    Zombie zombie;
    BucketHeadZombie bucketHead;
    ConeHeadZombie coneHeadZombie;
    DolphinRiderZombie dolphinRiderZombie;
    DuckyTubeZombie duckyTubeZombie;
    FootballZombie footballZombie;
    NewsPaperZombie newsPaperZombie;
    NormalZombie normalZombie;
    PoleVaultingZombie poleVaultingZombie;
    ScreenDoorZombie screenDoorZombie;
    YetiZombie yetiZombie;

    static String yellow = "\033[33m";   // Kode ANSI untuk warna kuning
    static String reset = "\u001B[0m";   // Kode ANSI untuk mereset warna
    static String red = "\u001B[31m";    // Kode ANSI untuk warna merah
    static String green = "\u001B[32m";  // Kode ANSI untuk warna hijau

    public ZombiesList() {
        bucketHead = new BucketHeadZombie();
        coneHeadZombie = new ConeHeadZombie();
        dolphinRiderZombie = new DolphinRiderZombie();
        duckyTubeZombie = new DuckyTubeZombie();
        footballZombie = new FootballZombie();
        newsPaperZombie = new NewsPaperZombie();
        normalZombie = new NormalZombie();
        poleVaultingZombie = new PoleVaultingZombie();
        normalZombie = new NormalZombie();
        poleVaultingZombie = new PoleVaultingZombie();
        screenDoorZombie = new ScreenDoorZombie();
        yetiZombie = new YetiZombie();

        System.out.println(yellow + "===========================ZOMBIES LIST==========================" + reset);
        System.out.println(" ");
        System.out.println(red + "1. NORMAL ZOMBIE" + reset);
        printZombiesDetails(normalZombie);
        System.out.println(red + "2. CONEHEAD ZOMBIE" + reset);
        printZombiesDetails(coneHeadZombie);
        System.out.println(red + "3. DOLPHIN RIDER ZOMBIE" + reset);
        printZombiesDetails(dolphinRiderZombie);
        System.out.println(red + "4. DUCKY TUBE ZOMBIE" + reset);
        printZombiesDetails(duckyTubeZombie);
        System.out.println(red + "5. FOOTBALL ZOMBIE" + reset);
        printZombiesDetails(footballZombie);
        System.out.println(red + "6. NEWSPAPER ZOMBIE" + reset);
        printZombiesDetails(newsPaperZombie);
        System.out.println(red + "7. POLE VAULTING ZOMBIE" + reset);
        printZombiesDetails(poleVaultingZombie);
        System.out.println(red + "8. SCREEN DOOR ZOMBIE" + reset);
        printZombiesDetails(screenDoorZombie);
        System.out.println(red + "9. BUCKETHEAD ZOMBIE" + reset);
        printZombiesDetails(bucketHead);
        System.out.println(red + "10. YETI ZOMBIE" + reset);
        printZombiesDetails(yetiZombie);
        System.out.println(yellow + "================================================================" + reset);
    }
    
    public void printZombiesDetails(Zombie zombie) {
        System.out.println(green + "Name: " + reset + zombie.getName());
        System.out.println(green + "Health: " + reset + zombie.getHealth());
        System.out.println(green + "Attack Damage: " + reset + zombie.getAttackDamage());
        System.out.println(green + "Attack Speed: " + reset + zombie.getAttackSpeed());
        System.out.println(" ");
    }
}
