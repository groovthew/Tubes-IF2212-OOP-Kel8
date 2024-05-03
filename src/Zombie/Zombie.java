package Zombie;

import Main.Character;

public abstract class Zombie extends Character {
    private int walking_speed;
    private boolean isAquatic;
    private boolean is_slow;

    public Zombie(String name, int health, int attack_damage, int attack_speed, int walking_speed, boolean isAquatic, boolean is_slow){
        super(name, health, attack_damage, attack_speed);
        this.walking_speed = walking_speed;
        this.isAquatic = isAquatic;
        this.is_slow = is_slow;
    }

    //getter
    public int getWalkingSpeed(){ return walking_speed;}
    public boolean getIsAquatic(){ return isAquatic;}
    public boolean getIsSlow(){ return is_slow;}

    //setter
    public void setWalkingSpeed(int walking_speed){ this.walking_speed = walking_speed;}
}
