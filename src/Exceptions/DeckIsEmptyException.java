package Exceptions;

public class DeckIsEmptyException extends Exception {
    public DeckIsEmptyException() {
        super("Deck kosong! Tidak dapat memulai permainan.");
    }
}
