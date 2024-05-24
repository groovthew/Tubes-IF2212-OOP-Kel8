package Exceptions;

public class NoZombieInTileException extends Exception{
    public NoZombieInTileException() {
        super("There are no zombie in the tile.");
    }
}
