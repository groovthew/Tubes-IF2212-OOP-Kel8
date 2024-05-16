package Exceptions;

public class DeckIsFull extends Exception {
    public DeckIsFull() {
        super("Deck penuh! Tidak dapat menambahkan tanaman baru.");
    }
}
