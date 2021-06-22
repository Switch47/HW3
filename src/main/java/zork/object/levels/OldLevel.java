package zork.object.levels;

import zork.Game;
import zork.object.Room;
import zork.object.item.Item;
import zork.object.item.ItemFactory;

public class OldLevel extends Level {

    public OldLevel() {
        name = ("level1");
        objective = ("pick up a weapon" + "\n" + "You can choose only one weapon.");

        ItemFactory itemFactory = new ItemFactory();

        Room room1 = new Room();
        Room room2 = new Room();
        Room room3 = new Room();

        Item longSword = itemFactory.createLongSword();
        Item sword = itemFactory.createSword();
        Item knife = itemFactory.createKnife();
        Item potion = itemFactory.createPotion();

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
