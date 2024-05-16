package Main;

import Tanaman.Plant;
import java.util.ArrayList;
import java.util.List;
import Exceptions.CantBePlantedException;
import java.util.Scanner;

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
        return i < 0 || i > deck.size() || deck.get(i) == null;
    }

    public void addPlant(Plant plant) throws CantBePlantedException {
        if (deck.size() <= MAX_PLANTS) {
            deck.add(plant);
            System.out.println(plant.getName() + " berhasil ditambahkan ke Deck!");
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
        } else {
            System.out.println("Indeks tidak valid. Tukar tidak dapat dilakukan.");
        }
    }

    public void addPlantFromInventory(String plantName, Inventory inventory) throws CantBePlantedException {
        Plant plant = inventory.getPlantByName(plantName);
        if (plant != null) {
            addPlant(plant);
        } else {
            throw new CantBePlantedException();
        }
    }

    public void displayDeck() {
        int i = 1;
        if (!deck.isEmpty()) {
            for (Plant plant: deck) {
                System.out.println(i + ". " + deck.get(i-1).getName());
                i++;
            }
        } else {
            System.out.println("Deck kosong");
        }
    }

    public static void main(String[] args) {
        try {
            Inventory inventory = new Inventory();
            Plant plant1 = new Plant("Peashooter", 50, 20, 1, 50, 1, 5);
            Plant plant2 = new Plant("Sunflower", 30, 0, 0, 25, 1, 7);
            Plant plant3 = new Plant("Walnut", 150, 0, 0, 50, 1, 10);
            Plant plant4 = new Plant("SnowPea", 100, 25, 4, 175, -1, 10);
            Plant plant5 = new Plant("Chomper", 1000, 1000, 0, 150, 0, 10);
            Plant plant6 = new Plant("Squash", 100, 5000, 0, 50, 1, 20);
            Plant plant7 = new Plant("Jalapeno", 100, 0, 0, 150, 0, 20);
            Plant plant8 = new Plant("Tall-nut", 2000, 0, 0, 100, 0, 20);
            Plant plant9 = new Plant("Lilypad", 100, 25, 4, 100, -1, 10);
            Plant plant10 = new Plant("Sunshroom", 100, 0, 0, 15, 0, 5);

            inventory.addPlantToInventory(plant1);
            inventory.addPlantToInventory(plant2);
            inventory.addPlantToInventory(plant3);
            inventory.addPlantToInventory(plant4);
            inventory.addPlantToInventory(plant5);
            inventory.addPlantToInventory(plant6);
            inventory.addPlantToInventory(plant7);
            inventory.addPlantToInventory(plant8);
            inventory.addPlantToInventory(plant9);
            inventory.addPlantToInventory(plant10);

            Deck deck = new Deck();
            Scanner scanner = new Scanner(System.in);

            while (deck.getDeck().size() < Deck.MAX_PLANTS) {
                inventory.displayInventory();
                System.out.print("Masukkan tanaman yang ingin ditambahkan ke deck: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("selesai")) {
                    break;
                }
                try {
                    int index = Integer.parseInt(input) - 1; // Kurangi 1 untuk mengubah ke indeks berbasis 0
                    inventory.addPlantToDeck(index, deck);
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Masukkan angka.");
                } catch (CantBePlantedException e) {
                    System.out.println(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Indeks tidak valid. Masukkan indeks yang sesuai.");
                }
            }

            System.out.println("Deck sebelum swap:");
            deck.displayDeck();

            System.out.print("Masukkan indeks pertama untuk swap: ");
            int index1 = scanner.nextInt() - 1; // Kurangi 1 untuk mengubah ke indeks berbasis 0
            System.out.print("Masukkan indeks kedua untuk swap: ");
            int index2 = scanner.nextInt() - 1; // Kurangi 1 untuk mengubah ke indeks berbasis 0
            deck.swapDeck(index1, index2);

            System.out.println("Deck setelah swap:");
            deck.displayDeck();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}