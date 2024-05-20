package Map;

import Tanaman.*;

public class PlantSymbol {
    public static String getPlantSymbol(Plant plant) {
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
        } else if (plant instanceof TwinSunflower) {
            return "TS";
        } else if (plant instanceof TallNut) {
            return "TN";
        } else if (plant instanceof Jalapeno) {
            return "JP";
        } else if (plant instanceof Lilypad) {
            return "LL";
        } else if (plant instanceof WallNut) {
            return "WN";
        }
        return "??"; // Simbol default jika jenis tanaman tidak dikenali
    }
}
