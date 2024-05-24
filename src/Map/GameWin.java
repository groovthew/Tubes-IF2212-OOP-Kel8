package Map;

public class GameWin {
    static String yellow = "\033[33m";   // Kode ANSI untuk warna kuning
    static String reset = "\u001B[0m";  // Kode ANSI untuk mereset warna
    static String green = "\u001B[32m";  // Kode ANSI untuk warna hijau
    public void displayGameWin() {
        System.out.println(yellow + "██████╗ ██████╗  ███╗   ██╗ ██████╗ ██████╗  █████╗ ████████╗███████╗██╗    ██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗██╗███╗   ██╗   " + reset);
        System.out.println(yellow + "██╔════╝██╔═══██╗████╗  ██║██╔════╝ ██╔══██╗██╔══██╗╚══██╔══╝██╔════╝██║    ╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██║████╗  ██║   " + reset);
        System.out.println(yellow + "██║     ██║   ██║██╔██╗ ██║██║  ███╗██████╔╝███████║   ██║   ███████╗██║     ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║██║██╔██╗ ██║   " + reset);
        System.out.println(yellow + "██║     ██║   ██║██║╚██╗██║██║   ██║██╔══██╗██╔══██║   ██║   ╚════██║╚═╝      ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██║██║╚██╗██║   " + reset);
        System.out.println(yellow + "╚██████╗╚██████╔╝██║ ╚████║╚██████╔╝██║  ██║██║  ██║   ██║   ███████║██╗       ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝██║██║ ╚████║██╗" + reset);
        System.out.println(yellow + " ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝ ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝       ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝╚═╝" + reset);
    }
}
