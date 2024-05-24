package Zombie;

public class NewsPaperZombie extends Zombie{
    private boolean isNewspaperDestroyed;
    
    public NewsPaperZombie(){
        super("Newspaper Zombie", 200 , 100, 1);
        this.isNewspaperDestroyed = false;
        angry();
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
        if (this.getHealth() < 100){
            System.out.println("News Paper Zombie is now angry");
            super.setAttackDamage(200);
        }
    }

    public static void main(String[] args) {
        NewsPaperZombie nz = new NewsPaperZombie();
        // Example of newspaper getting destroyed
        nz.setNewspaperDestroyed(true);
    }
}
