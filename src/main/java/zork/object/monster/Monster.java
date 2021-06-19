package zork.object.monster;

public class Monster {


    public String name;
    public int HP;
    public int attackPower;
    public double hitProb;
    public boolean alive;
    public int level;
    public int exp;

    public Monster(String name, int HP, int attackPower, double hitProb, int level, int exp) {
        this.name = name;
        this.HP = HP;
        this.attackPower = attackPower;
        this.hitProb = hitProb;
        this.level = level;
        this.exp = exp;
    }

    public void hpDecrease(int hp) {
        HP = HP - hp;
        if (HP <= 0) {
            alive = false;
        }
    }

    public void levelUp(int level) {
        int increasePower = attackPower*level;
        int increaseHP = HP*level;
        attackPower = increasePower;
        HP = increaseHP;
    }

    public void gainEXP(int exp) {
        if (exp >= 100) {
            this.exp = 0;
            level = level+1;
            levelUp(level);
        }
        else {
            this.exp = this.exp + exp;
        }
    }
}
