package Zombie;

import Main.Character;


public class Zombie extends Character {
    private boolean isAquatic;
    private boolean is_slowed;
    private double speed;
    private boolean isCooldown = false;

    public Zombie(String name, int health, int attack_damage, int attack_speed){
        super(name, health, attack_damage, 1);
        this.isAquatic = false;
        this.is_slowed = false;
    }

    public Zombie(String name, int health, int attack_damage, int attack_speed, boolean isAquatic){
        super(name, health, attack_damage, 1);
        this.isAquatic = isAquatic;
        this.is_slowed = false;
    }

    //getter
    public boolean getIsAquatic(){ return isAquatic;}
    public boolean getIsSlow(){ return is_slowed;}

    public boolean isSlowed() {
        return is_slowed;
    }

    public void setSlowed(boolean slowed) {
        this.is_slowed = slowed;
    }

    public double getAttackSpeed() {
        return super.getAttackSpeed();
    }

    public void setAttackSpeed(double attackSpeed) {
        super.setAttackSpeed(attackSpeed);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isCooldown() {
        return isCooldown;
    }

    public void setIsCooldown(boolean isCooldown) {
        this.isCooldown = isCooldown;
    }
}
