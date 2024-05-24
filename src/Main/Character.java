package Main;

public abstract class Character {
    protected String name;
    private int health;
    private int attack_damage;
    private double attack_speed;
    private int x;
    private int y;

    public Character(String name, int health, int attack_damage, int attack_speed){
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
    }

    //getter
    public String getName(){ return name;}
    public int getHealth(){ return health;}
    public int getAttackDamage(){ return attack_damage;}
    public double getAttackSpeed(){ return attack_speed;}
    public int getX() { return x;}
    public int getY() { return y;}

    //setter
    public void setHealth(int health){
        this.health = health;
    } 
    public void setAttackSpeed(double attack_speed){
        this.attack_speed = attack_speed;
    }
    public void setAttackDamage(int attack_damage){
        this.attack_damage = attack_damage;
    }
}
