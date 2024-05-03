package Main;

public abstract class Character {
    private String name;
    private int health;
    private int attack_damage;
    private int attack_speed;

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
    public int getAttackSpeed(){ return attack_speed;}
}
