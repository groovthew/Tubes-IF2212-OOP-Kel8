package Exceptions;

public class CantBePlantedException extends Exception {
    public CantBePlantedException() {
        super("Tanaman tidak bisa ditambahkan.");
    }
}
