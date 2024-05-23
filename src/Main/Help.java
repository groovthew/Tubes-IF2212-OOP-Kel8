package Main;

import java.util.Scanner;

public class Help {
    
    public static void main(String[] args) {
        displayHelp();
    }

    public static void displayHelp() {
        String green = "\u001B[32m";  // Kode ANSI untuk warna hijau
        String yellow = "\033[33m";   // Kode ANSI untuk warna kuning
        String reset = "\u001B[0m";   // Kode ANSI untuk mereset warna

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println(green+ "==========================================================" + reset);
        System.out.println(yellow + "                       HELP MENU                      " + yellow);
        System.out.println(green + "==========================================================" + reset);
        System.out.println();
        System.out.println(green + "=====================" + reset + yellow +" ABOUT GAME " + reset + green + "=========================" + reset);
        System.out.println("Michael vs Lalapan adalah permainan strategi di mana      ");
        System.out.println("pemain harus melindungi rumah mereka dari serangan zombie      ");
        System.out.println("dengan menanam berbagai jenis tanaman.      ");
        System.out.println("                                    ");
        System.out.println();
        System.out.println(green + "=====================" + reset + yellow +" HOW TO PLAY " + reset + green + "========================" + reset);
        System.out.println("1. Tanam berbagai jenis tanaman untuk melindungi  ");
        System.out.println("   rumah Anda.                                    ");
        System.out.println("2. Setiap tanaman memiliki kemampuan khusus untuk ");
        System.out.println("   menyerang atau menghalangi zombie.             ");
        System.out.println("3. Kumpulkan matahari untuk membeli tanaman baru. ");
        System.out.println("4. Zombie akan datang dalam gelombang, jadi       ");
        System.out.println("   pastikan untuk selalu siap.                    ");
        System.out.println();
        System.out.println(green + "==================" + reset + yellow +" LIST OF COMMANDS " + reset + green + "======================" + reset);
        System.out.println("1. START    : Memulai permainan dan mengakses opsi:  ");
        System.out.println("              - addPlant [jenisTanaman] [X] [Y]");
        System.out.println("                Menambah tanaman di koordinat yang ditentukan.");
        System.out.println("              - displayMap - Menampilkan peta permainan.     ");
        System.out.println("2. INVENTORY: Menampilkan dan mengelola inventory:");
        System.out.println("              - displayInventory - Menampilkan isi inventory.");
        System.out.println("              - swapInventory - Menukar item di inventory.  ");
        System.out.println("3. DECK     : Mengelola deck tanaman Anda:            ");
        System.out.println("              - swapDeck - Menukar deck.                     ");
        System.out.println("              - addPlant - Menambah tanaman ke deck.         ");
        System.out.println("              - removeDeck - Menghapus tanaman dari deck.    ");
        System.out.println("              - displayDeck - Menampilkan isi deck.          ");
        System.out.println("              - back - Kembali ke menu sebelumnya.           ");
        System.out.println("4. HELP     : Menampilkan menu bantuan ini.           ");
        System.out.println("5. EXIT     : Keluar dari permainan.                  ");
        System.out.println();
        System.out.println(green + "=========================================================" + reset);
        System.out.println("Ketik 'back' untuk kembali ke menu utama...");
        
        do {
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("back")) {
                break;
            } else {
                System.out.println("Invalid command. Please type 'back' to return to the main menu.");
            }
        } while (true);
    }
}
