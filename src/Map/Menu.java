package Map;

public class Menu {
    public void openingAct(){
        String green = "\u001B[32m";  // Kode ANSI untuk warna hijau
        String red = "\u001B[31m";    // Kode ANSI untuk warna merah
        String blue = "\u001B[34m";   // Kode ANSI untuk warna biru
        String reset = "\u001B[0m";   // Kode ANSI untuk mereset warna
        String[] asciiArt = new String[]{
    
            blue + "    ███╗   ███╗██╗ ██████╗██╗  ██╗ █████╗ ███████╗██╗         " + reset,
            blue + "    ████╗ ████║██║██╔════╝██║  ██║██╔══██╗██╔════╝██║         " + reset,
            blue + "    ██╔████╔██║██║██║     ███████║███████║█████╗  ██║         " + reset,
            blue + "    ██║╚██╔╝██║██║██║     ██╔══██║██╔══██║██╔══╝  ██║         " + reset,
            blue + "    ██║ ╚═╝ ██║██║╚██████╗██║  ██║██║  ██║███████╗███████╗    " + reset,
            blue + "    ╚═╝     ╚═╝╚═╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝    " + reset,
            red + "                    ██╗   ██╗███████╗                         " + reset,
            red + "                    ██║   ██║██╔════╝                         " + reset,
            red + "                    ██║   ██║███████╗                         " + reset,
            red + "                    ╚██╗ ██╔╝╚════██║                         " + reset,
            red + "                     ╚████╔╝ ███████║                         " + reset,
            red + "                      ╚═══╝  ╚══════╝                         " + reset,
            green + "██╗      █████╗ ██╗      █████╗ ██████╗  █████╗ ███╗   ██╗    " + reset,
            green + "██║     ██╔══██╗██║     ██╔══██╗██╔══██╗██╔══██╗████╗  ██║    " + reset,
            green + "██║     ███████║██║     ███████║██████╔╝███████║██╔██╗ ██║    " + reset,
            green + "██║     ██╔══██║██║     ██╔══██║██╔═══╝ ██╔══██║██║╚██╗██║    " + reset,
            green + "███████╗██║  ██║███████╗██║  ██║██║     ██║  ██║██║ ╚████║    " + reset,
            green + "╚══════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝  ╚═╝╚═╝  ╚═══╝    " + reset
        };
    
        for (String line : asciiArt) {
            System.out.println(line);
        }
    }
}
