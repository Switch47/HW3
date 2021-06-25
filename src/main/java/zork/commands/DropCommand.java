package zork.commands;

import zork.Game;
import java.util.List;
import java.util.Scanner;

public class DropCommand implements Command{
    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "drop";
    }

    @Override
    public void execute(Game game, List<String> argument) {
        if (Game.getGameStatus() == Game.PLAY_STATUS) {
            Scanner in = new Scanner(System.in);
            String item;
            System.out.println("Which items do you want to drop??");
            if (Game.player.weapon != null) {
                System.out.println("Weapon in your inventory : [ " + Game.player.weapon.name + " ]");
            } else {
                System.out.println("**There is no weapon in your inventory**");
            }
            if (Game.player.items.size() > 0) {
                StringBuilder x = new StringBuilder("Items in your Inventory : [ " + Game.player.items.get(0).name);
                for (int i = 1; i < Game.player.items.size(); i++) {
                    x.append(" , ").append(Game.player.items.get(i).name);
                }
                System.out.println(x + " ]");
            } else {
                System.out.println("**There is no any item in your inventory**");
            }

            item = in.nextLine();
            int check = 0;
            if (Game.player.items.size() > 0) {
                for (int i = 0; i < Game.player.items.size(); i++) {
                    if (item.equals(Game.player.items.get(i).name)) {
                        check += 1;
                    }
                }
                if (check > 0){
                    for (int i = 0; i < Game.player.items.size(); i++) {
                        if (item.equals(Game.player.items.get(i).name)) {
                            Game.currentRoom.setItems(Game.player.items.get(i));
                            Game.player.items.remove(Game.player.items.get(i));
                            System.out.println("[ " + item + " ] was dropped!!");
                            break;
                        }
                    }
                }
            }
            if (Game.player.weapon != null) {
                if (item.equals(Game.player.weapon.name)) {
                    Game.currentRoom.setItems(Game.player.weapon);
                    Game.player.weapon = null;
                    System.out.println("[ " + item + " ] was dropped!!");
                }
                else if (check == 0){
                    System.out.println("There is no [ " + item + " ] in your inventory");
                }
            }
            if (Game.player.weapon == null && Game.player.items.size() <= 0) {
                System.out.println("Your inventory is now empty!!");
            }
        }
        else {
            System.out.println("!!Cannot use this command in start menu!!");
        }
    }
}
