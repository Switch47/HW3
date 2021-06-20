package zork.object.item.weapons;

import zork.object.item.Item;

public class Weapon extends Item {

    public int agility;
    public int strength;
    public int attackTime;

    public Weapon(String name, int attackPower, int quickness, int attackTime) {
        this.name = name;
        this.strength = attackPower;
        this.agility = quickness;
        this.attackTime = attackTime;
        this.skill = "add" + attackPower + "strength and" + quickness + "agility to player's status";
    }
}
