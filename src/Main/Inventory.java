package Main;

import java.util.ArrayList;
import Tanaman.*;

public class Inventory {
    private ArrayList<Plant> container;
    private Deck deck;

  public Inventory() {
    this.container = new ArrayList<>();
    deck = new Deck();
  }

  public ArrayList<Plant> getInventory() {
    return container;
  }

  public Deck getDeck() {
    return deck;
  }

  public void swapInventory(int x, int y) {
    Plant plant = container.get(x);
    container.set(x, container.get(y));
    container.set(y, plant);
  }

  public void swapDeck(int x, int y) {
    Plant plant = deck.getDeck().get(x);
    deck.getDeck().set(x, deck.getDeck().get(y));
    deck.getDeck().set(y, plant);
  }

  public void addPlantToDeck(int i) {
    deck.getDeck().add(container.get(i));
  }

  public void addPlantToInventory(Plant plant) {
    container.add(plant);
  }

  public void displayInventory() {
    System.out.println("Inventory:");
    for (int i = 0; i < container.size(); i++) {
        Plant plant = container.get(i);
        System.out.println((i+1) + ". " + plant.getName());
    }
  }

  public static void main(String[] args) {
    Inventory inventory = new Inventory();
    inventory.addPlantToInventory(new Peashooter("Peashooter", 100, 25, 4, 100, -1, 10));
    inventory.addPlantToInventory(new Sunflower("Sunflower", 50, 0, 0, 50, 0, 10));
    inventory.addPlantToInventory(new Squash("Squash", 100, 5000, 0, 50, 1, 20));
    inventory.displayInventory();
}


}



























// package Grid;
// import java.util.ArrayList;
// import java.util.List;

// import Tanaman.Plant;

// public class Inventory <T extends Plant> implements Grid {
//     private List<T> container;
//     public Inventory(){
//         this.container = new ArrayList<>();
//     }
//     public List<T> getContainer() {
//         return container;
//     }

//     public boolean isEmpty() {
//         return container.isEmpty();
//     }

//     public void displayInventory() {
//         if (isEmpty()) {
//             System.out.println("Inventory is empty.");
//         } else {
//             System.out.println("Inventory contains:");
//             for (T plant : container) {
//                 System.out.println(plant.getName());
//             }
//         }
//     }

//     @Override
//     public void addPlant(T plant) {
//         container.add(plant);
//     }

//     @Override
//     public void removePlant() {

//     }

//     @Override
//     public void switchPlant() {

//     }
//     @Override
//     public void addPlant() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'addPlant'");
//     }
//     @Override
//     public void removeZombie() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'removeZombie'");
//     }
//     @Override
//     public boolean hasZombie() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'hasZombie'");
//     }
// }
