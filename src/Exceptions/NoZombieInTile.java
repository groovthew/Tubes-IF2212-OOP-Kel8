package Exceptions;

public class NoZombieInTile extends Exception{
    public NoZombieInTile() {
        super("There are no zombie in the tile.");
    }
}
