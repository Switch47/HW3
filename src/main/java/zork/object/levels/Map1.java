package zork.object.levels;

import zork.Game;
import zork.object.Room;
import zork.object.item.ItemFactory;
import zork.object.item.potions.Potion;
import zork.object.item.weapons.Weapon;

public class Map1 extends Map {

    public Map1() {
        name = ("level1");
        objective = ("pick up a weapon" + "\n" + "You can choose only one weapon.");

        ItemFactory itemFactory = new ItemFactory();

        Room room1 = new Room();
        Room room2 = new Room();
        Room room3 = new Room();

        Weapon longSword = itemFactory.createLongSword();
        Weapon sword = itemFactory.createSword();
        Weapon knife = itemFactory.createKnife();
        Potion potion = itemFactory.createPotion();

        room1.setItems(sword);
        room2.setItems(longSword);
        room3.setItems(knife);
        room2.setItems(potion);


        room1.setRightRoom(room2);
        room2.setRightRoom(room3);

        startRoom = room2;

    }

    @Override
    public boolean objectiveCompleted() {
        if (Game.player.weapon != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
