package Main;

public abstract class Character {
    private String name;
    private int health;
    private int attack_damage;
    private int attack_speed;
    private int x;
    private int y;

    public Character(String name, int health, int attack_damage, int attack_speed, int x, int y){
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.x = x;
        this.y = y;
    }

    //getter
    public String getName(){ return name;}
    public int getHealth(){ return health;}
    public int getAttackDamage(){ return attack_damage;}
    public int getAttackSpeed(){ return attack_speed;}
    public int getX() { return x;}
    public int getY() { return y;}
}
