package zork.object.monster;

import zork.object.Player;

public class Monster {


    public String name;
    public int HP;
    public int strength;
    public double hitProb;
    public boolean alive = true;
    public int agility;
    public int expDrop;
    public int level;
    public int MAX_HP;

    public Monster(String name, int HP, int strength, int agility, double hitProb, int level, int expDrop, int max_HP) {
        this.name = name;
        this.HP = HP;
        this.strength = strength;
        this.hitProb = hitProb;
        this.agility = agility;
        this.level = level;
        this.expDrop = expDrop*level;
        this.MAX_HP = max_HP;
    }

    public void decreaseHP(int hp) {
        HP = HP - hp;
        if (HP < 1) {
            alive = false;
        }
    }

    public void attack(Player player) {
        double randomNum = Math.random();
        if(randomNum < this.hitProb) {
            player.decreaseHP(this.strength);
            System.out.println("ACTION : Monster is attacking you.");
            System.out.println("Player HP Remaining : " + player.HP);
        }
        else {
            System.out.println("Monster Attack Misses!!!");
        }
    }
}
