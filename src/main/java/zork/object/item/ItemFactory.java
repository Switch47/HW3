package zork.object.item;


import zork.object.item.potions.Potion;
import zork.object.item.potions.SuperPotion;
import zork.object.item.weapons.*;

public class ItemFactory {

    public Weapon createWeapon(String name, int attackPower, int quickness, int attackTime) {
        return new Weapon(name,attackPower,quickness,attackTime);
    }
    public Sword createSword() {
        return new Sword();
    }
    public LongSword createLongSword() {
        return new LongSword();
    }
    public Gun createGun() {
        return new Gun();
    }
    public Knife createKnife() {
        return new Knife();
    }
    public Potion createPotion() {
        return new Potion();
    }
    public SuperPotion createSuperPotion() {
        return new SuperPotion();
    }
}
