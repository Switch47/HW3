package zork.object.item.potions;

import zork.Game;
import zork.object.item.Item;

public class HealITem extends Item {


    public HealITem(String name, int addHP) {
        this.name = name;
        this.addHP = addHP;
        this.skill = "Successful Healing " + addHP;
    }
}
