package Zombie;

public class DolphinRiderZombie extends Zombie {
    private boolean isJumped;
    
    public DolphinRiderZombie(){
        super("Dolphin Rider Zombie", 175, 100, 1, true);  
        this.isJumped = false;  
    }
    
    public boolean isJumped() {
        return isJumped;
    }

    public void setJumped(boolean isJumped) {
        this.isJumped = isJumped;
    }

    public void jump() {
        if (!isJumped) {
            System.out.println("Dolphin Rider Zombie jumps over the first plant!");
            this.setJumped(true);
            // this.setSpeed(this.getSpeed() / 2); // ini hrsnya thread jg
        }
    }
    public static void main(String[] args) {
        DolphinRiderZombie drz = new DolphinRiderZombie();
        drz.jump();
    }
}
