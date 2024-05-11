package Main;

import Tanaman.Plant;
import java.util.ArrayList;
import java.util.List;

import Exceptions.CantBePlantedException;

public class Deck {
    private ArrayList<Plant> deck;
    private static final int MAX_PLANTS = 6;
    
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
        return deck.get(i) == null;
    }

    public void addPlant(Plant plant) throws CantBePlantedException {
        if (deck.size() <= MAX_PLANTS) {
            deck.add(plant);
            System.out.println(plant.getName() + "berhasil ditambahkan ke deck");
        } else {
            
        }
    }

    public void displayDeck() {
        int i = 1;
        if (!deck.isEmpty()) {
            for (Plant plant: deck) {
                System.out.println(i + "." + deck.get(i-1).getName());
                i++;
            }
        } else {
            System.out.println("Deck kosong");
        }
    }
}