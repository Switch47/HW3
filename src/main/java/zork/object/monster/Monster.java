package zork.object.monster;

import zork.object.Player;

public class Monster {


    public String name;
    public int HP;
    public int attackPower;
    public double hitProb;
    public boolean alive;
    public int agility;
    public int expDrop;
    public int level;

    public Monster(String name, int HP, int attackPower, int agility, double hitProb, int level, int expDrop) {
        this.name = name;
        this.HP = HP;
        this.attackPower = attackPower;
        this.hitProb = hitProb;
        this.agility = agility;
        this.level = level;
        this.expDrop = expDrop*level;
    }

    public void decreaseHP(int hp) {
        HP = HP - hp;
        if (HP <= 0) {
            alive = false;
        }
    }

    public void attack(Player player) {
        double randomNum = Math.random();
        if(randomNum < this.hitProb) {
            player.decreaseHP(this.attackPower);
            System.out.println("Player HP Remaining : " + player.HP);
        }
        else {
            System.out.println("Monster Attack Misses!!!");
        }
    }
}
