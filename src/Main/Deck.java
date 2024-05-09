package Main;

import Tanaman.Plant;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private ArrayList<Plant> deck;
    
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