package Exceptions;

public class CantSwapDeckException extends Exception {
    public CantSwapDeckException() {
         super("Deck kosong! Tidak dapat melakukan swap.");
    }
}