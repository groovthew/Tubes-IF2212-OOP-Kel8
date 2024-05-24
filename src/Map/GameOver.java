package Map;

public class GameOver {
    static String yellow = "\033[33m";   // Kode ANSI untuk warna kuning
    static String reset = "\u001B[0m";  // Kode ANSI untuk mereset warna
    static String green = "\u001B[32m";  // Kode ANSI untuk warna hijau

    public void displayGameOver() {
        System.out.println(yellow + "███████╗██╗  ██╗ █████╗ ███╗   ███╗███████╗       ██╗   ██╗ ██████╗ ██╗   ██╗    ██╗      ██████╗ ███████╗███████╗   " + reset);
        System.out.println(yellow + "██╔════╝██║  ██║██╔══██╗████╗ ████║██╔════╝       ╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║     ██╔═══██╗██╔════╝██╔════╝   " + reset);
        System.out.println(yellow + "███████╗███████║███████║██╔████╔██║█████╗          ╚████╔╝ ██║   ██║██║   ██║    ██║     ██║   ██║███████╗█████╗     " + reset);
        System.out.println(yellow + "╚════██║██╔══██║██╔══██║██║╚██╔╝██║██╔══╝           ╚██╔╝  ██║   ██║██║   ██║    ██║     ██║   ██║╚════██║██╔══╝     " + reset);
        System.out.println(yellow + "███████║██║  ██║██║  ██║██║ ╚═╝ ██║███████╗▄█╗       ██║   ╚██████╔╝╚██████╔╝    ███████╗╚██████╔╝███████║███████╗██╗" + reset);
        System.out.println(yellow + "╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝╚═╝       ╚═╝    ╚═════╝  ╚═════╝     ╚══════╝ ╚═════╝ ╚══════╝╚══════╝╚═╝" + reset);
    }

    public static void main(String[] args) {
        GameOver print = new GameOver();
        print.displayGameOver();
    }
}
