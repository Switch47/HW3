package zork.object.monster;

public class MonsterFactroy {

    public Monster makeMonster(String name, int HP, int attackPower, int agility, double hitProb, int level, int expDrop) {
        return new Monster(name,HP,attackPower,agility,hitProb,level,expDrop);
    }
    public Zombie makeZombie() {
        return new Zombie();
    }
}
