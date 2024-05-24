package Zombie;

public class NewsPaperZombie extends Zombie {
    private boolean isNewspaperDestroyed;

    public NewsPaperZombie() {
        super("Newspaper Zombie", 200, 100, 1);
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

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if (health <= 100 && !isNewspaperDestroyed) {
            setNewspaperDestroyed(true);
        }
    }

    public void angry() {
        if (getHealth() <= 100) {
            setAttackDamage(200);
            System.out.println("NewsPaperZombie marah karena korannya hancur, dia bertambah damage menjadi 200!!");
        }
    }
}
