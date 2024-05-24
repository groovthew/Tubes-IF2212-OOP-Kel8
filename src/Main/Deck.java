package Main;

import Tanaman.*;
import java.util.ArrayList;
import java.util.List;
import Exceptions.CantBePlantedException;

public class Deck {
    private ArrayList<Plant> deck;
    public static final int MAX_PLANTS = 6;

    public Deck() {
        deck = new ArrayList<>();
    }

    public List<Plant> getDeck() {
        return deck;
    }

    public boolean isDeckEmpty() {
        return deck.isEmpty();
    }

    public boolean isSlotEmpty(int i) {
        return i < 0 || i >= deck.size() || deck.get(i) == null;
    }

    public void addPlant(Plant plant) throws CantBePlantedException {
        if (deck.size() < MAX_PLANTS) {
            deck.add(plant);
        } else {
            throw new CantBePlantedException();
        }
    }

    public void swapDeck(int index1, int index2) {
        if (index1 >= 0 && index1 < deck.size() && index2 >= 0 && index2 < deck.size()) {
            Plant temp = deck.get(index1);
            deck.set(index1, deck.get(index2));
            deck.set(index2, temp);
            System.out.println("Tanaman pada posisi " + (index1 + 1) + " dan " + (index2 + 1) + " telah ditukar.");
        }
        else {
            System.out.println("Indeks tidak valid. Tukar tidak dapat dilakukan.");
        }
    }

    public void addPlantFromInventory(int plantIndex, Inventory inventory, Deck deck) throws CantBePlantedException {
        if (plantIndex >= 0 && plantIndex < inventory.getInventory().size()) {
            Plant plant = inventory.getInventory().get(plantIndex);
            if (deck.isPlantInDeck(plant.getName())) {
                System.out.println("Tanaman tersebut sudah ada di deck.");
                return;
            }
            deck.addPlant(plant);
            System.out.println("Tanaman " + plant.getName() + " berhasil ditambahkan ke deck.");
        } else {
            System.out.println("Indeks tanaman tidak valid.");
        }
    }

    public void removePlant(int index) {
        if (index >= 0 && index < deck.size()) {
            Plant removedPlant = deck.remove(index);
            System.out.println(removedPlant.getName() + " telah dihapus dari Deck.");
        } else {
            System.out.println("Indeks tidak valid. Penghapusan tidak dapat dilakukan.");
        }
    }

    public boolean isPlantInDeck(String plantName) {
        for (Plant plant : deck) {
            if (plant.getName().equalsIgnoreCase(plantName)) {
                return true;
                }
            }
        return false; 
    }

    public boolean isPlantMatchDeck(String plantCode) {
        switch (plantCode.toUpperCase()) {
            case "PS":
                return this.isPlantInDeck("Peashooter");
            case "SF":
                return this.isPlantInDeck("Sunflower");
            case "CH":
                return this.isPlantInDeck("Chomper");
            case "SP":
                return this.isPlantInDeck("Snow Pea");
            case "SQ":
                return this.isPlantInDeck("Squash");
            case "TS":
                return this.isPlantInDeck("Twin Sunflower");
            case "TN":
                return this.isPlantInDeck("Tall-nut");
            case "JP":
                return this.isPlantInDeck("Jalapeno");
            case "LL":
                return this.isPlantInDeck("Lilypad");
            case "WN":
                return this.isPlantInDeck("Wall-nut");
            default:
                return false;
        }
    }
    public void clearDeck(){
        deck.clear();
    }
    public void displayDeck() {
        if (!deck.isEmpty()) {
            for (int i = 0; i < deck.size(); i++) {
                Plant plant = deck.get(i);
                System.out.println((i + 1) + ". " + plant.getName() + " " + plant.getCost());
            }
        } else {
            System.out.println("Deck kosong");
        }
    }
}
