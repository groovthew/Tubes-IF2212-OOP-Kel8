package Main;
import Zombie.*;

public class zombiesList {
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
    public zombiesList() {
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

        System.out.println(yellow + "================== ZOMBIE LIST =====================" + reset);
        System.out.println("1. Normal Zombie");
        printZombiesDetails(normalZombie);
        System.out.println("2. Conehead Zombie");
        printZombiesDetails(coneHeadZombie);
        System.out.println("3. Dolphin Rider Zombie");
        printZombiesDetails(dolphinRiderZombie);
        System.out.println("4. Ducky Tube Zombie");
        printZombiesDetails(duckyTubeZombie);
        System.out.println("5. Football Zombie");
        printZombiesDetails(footballZombie);
        System.out.println("6. Newspaper Zombie");
        printZombiesDetails(newsPaperZombie);
        System.out.println("7. Pole Vaulting Zombie");
        printZombiesDetails(poleVaultingZombie);
        System.out.println("8. Screen Door Zombie");
        printZombiesDetails(screenDoorZombie);
        System.out.println("9. Bucket Head Zombie");
        printZombiesDetails(bucketHead);
        System.out.println("10. Yeti Zombie");
        printZombiesDetails(yetiZombie);
    }

    public void printZombiesDetails(Zombie zombie) {
        System.out.println("Name: " + zombie.getName());
        System.out.println("Health: " + zombie.getHealth());
        System.out.println("Attack Damage: " + zombie.getAttackDamage());
        System.out.println("Attack Speed: " + zombie.getAttackSpeed());
        System.out.println(" ");
    }

    public static void main(String[] args) {
        new zombiesList();
    }
}
