package zork.object;

import zork.object.item.Item;
import zork.object.monster.Monster;

import java.util.ArrayList;
import java.util.List;

public class Room {

    public Room upRoom;
    public Room downRoom;
    public Room leftRoom;
    public Room rightRoom;

    public List<Item> items;
//    public Item item;
    public Monster monster;

    public Room() {
        upRoom = null;
        downRoom = null;
        leftRoom = null;
        rightRoom = null;

//        this.item = null;
        this.monster = null;
        items = new ArrayList<>();
    }

    public void setUpRoom(Room upRoom) {
        this.upRoom = upRoom;
        upRoom.downRoom = this;
    }

    public void setDownRoom(Room downRoom) {
        this.downRoom = downRoom;
        downRoom.upRoom = this;
    }

    public void setLeftRoom(Room leftRoom) {
        this.leftRoom = leftRoom;
        leftRoom.rightRoom = this;
    }

    public void setRightRoom(Room rightRoom) {
        this.rightRoom = rightRoom;
        rightRoom.leftRoom = this;
    }

//    public void setItem(Item item) {
//        this.item = item;
//    }

    public void setItems(Item item) {
        this.items.add(item);
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

}
