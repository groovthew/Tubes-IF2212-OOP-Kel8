package Main;

import java.util.Scanner;

public class Help {
    
    public static void main(String[] args) {
        displayHelp();
    }

    public static void displayHelp() {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("==================================================");
        System.out.println("                   HELP MENU                  ");
        System.out.println("==================================================");
        System.out.println();
        System.out.println("============== Deskripsi Permainan ===============");
        System.out.println("Michael vs Lalapan adalah permainan strategi      ");
        System.out.println("di mana pemain harus melindungi rumah mereka      ");
        System.out.println("dari serangan zombie dengan menanam berbagai      ");
        System.out.println("jenis tanaman.                                    ");
        System.out.println();
        System.out.println("=============== Arahan Cara Bermain ===============");
        System.out.println("1. Tanam berbagai jenis tanaman untuk melindungi  ");
        System.out.println("   rumah Anda.                                    ");
        System.out.println("2. Setiap tanaman memiliki kemampuan khusus untuk ");
        System.out.println("   menyerang atau menghalangi zombie.             ");
        System.out.println("3. Kumpulkan matahari untuk membeli tanaman baru. ");
        System.out.println("4. Zombie akan datang dalam gelombang, jadi       ");
        System.out.println("   pastikan untuk selalu siap.                    ");
        System.out.println();
        System.out.println("======== Daftar Command yang Dapat Dipakai ========");
        System.out.println("1. start - Memulai permainan dan mengakses opsi:  ");
        System.out.println("   - addPlant [jenisTanaman] [koordinatX] [koordinatY]");
        System.out.println("     Menambah tanaman di koordinat yang ditentukan.");
        System.out.println("   - displayMap - Menampilkan peta permainan.     ");
        System.out.println("2. inventory - Menampilkan dan mengelola inventory:");
        System.out.println("   - displayInventory - Menampilkan isi inventory.");
        System.out.println("   - swapInventory - Menukar item di inventory.  ");
        System.out.println("3. deck - Mengelola deck tanaman Anda:            ");
        System.out.println("   - swapDeck - Menukar deck.                     ");
        System.out.println("   - addPlant - Menambah tanaman ke deck.         ");
        System.out.println("   - removeDeck - Menghapus tanaman dari deck.    ");
        System.out.println("   - displayDeck - Menampilkan isi deck.          ");
        System.out.println("   - back - Kembali ke menu sebelumnya.           ");
        System.out.println("4. help - Menampilkan menu bantuan ini.           ");
        System.out.println("5. exit - Keluar dari permainan.                  ");
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Ketik 'back' untuk kembali ke menu utama...");
        
        do {
            input = scanner.nextLine().trim();
        } while (!input.equalsIgnoreCase("back"));

        scanner.close();
    }
}
