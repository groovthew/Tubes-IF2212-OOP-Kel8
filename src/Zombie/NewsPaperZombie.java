package Zombie;

public class NewsPaperZombie extends Zombie{
    private boolean isNewspaperDestroyed;
    
    public NewsPaperZombie(){
        super("News Paper Zombie", 200 , 100, 1);
        this.isNewspaperDestroyed = false;
    }

    public boolean isNewspaperDestroyed() {
        return isNewspaperDestroyed;
    }

    public void setNewspaperDestroyed(boolean isNewspaperDestroyed) {
        this.isNewspaperDestroyed = isNewspaperDestroyed;
        if (isNewspaperDestroyed) {
            angry();
        }
    }

    public void angry() {
        // Logic for the zombie becoming enraged and moving faster
        System.out.println("News Paper Zombie is now angry and moving faster!");
        // Increase the speed or any other attribute changes
        // this.setWalkingSpeed(this.getWalkingSpeed() * 2); // ini gabisa gini, pake thread?
    }

    public static void main(String[] args) {
        NewsPaperZombie nz = new NewsPaperZombie();
        // Example of newspaper getting destroyed
        nz.setNewspaperDestroyed(true);
    }
}
