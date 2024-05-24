package Exceptions;

public class DeckIsFullException extends Exception {
    public DeckIsFullException() {
        super("Deck penuh! Tidak dapat menambahkan tanaman baru.");
    }
}
