package zork.object.monster;

public class MonsterFactory {

    public Monster makeMonster(String name, int HP, int attackPower, int agility, double hitProb, int level, int expDrop, int max_HP) {
        return new Monster(name,HP,attackPower,agility,hitProb,level,expDrop,max_HP);
    }
    public Zombie makeZombie() {
        return new Zombie();
    }
    public Werewolf makeWerewolf() {return new Werewolf();}
    public Sephiroth makeSephiroth() {return new Sephiroth();}
}
