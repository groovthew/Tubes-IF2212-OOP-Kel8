package Exceptions;

public class DeckNotFullException extends Exception {
    public DeckNotFullException() {
        super("Deck belum terisi penuh! Pastikan Deck sudah terisi penuh sebelum memulai permainan.");
    }
}
