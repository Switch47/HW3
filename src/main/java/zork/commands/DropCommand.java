package zork.commands;

import zork.Game;
import zork.object.item.Item;
import zork.object.item.weapons.Weapon;

import java.util.ArrayList;
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
        List<Item> newItems = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String item;
        System.out.println("Which items do you want to drop??");
        if (game.player.weapon != null) {
            System.out.println("Weapon in your inventory : [ " + game.player.weapon.name + " ]");
        }
        else {
            System.out.println("**There is no weapon in your inventory**");
        }
        if (game.player.items.size() > 0) {
            String x = "Items in your Inventory : [ " + game.player.items.get(0).name;
            for (int i = 1; i < game.player.items.size(); i++) {
                x += " , " + game.player.items.get(i).name;
            }
            System.out.println(x+" ]");
        }
        else {
            System.out.println("**There is no any item in your inventory**");
        }

        item = in.nextLine();
        if (game.player.weapon != null) {
            if (item.equals(game.player.weapon.name)) {
                game.currentRoom.setItems(game.player.weapon);
                game.player.weapon = null;
                System.out.println(item + " was dropped!!");
            }
            else {
                System.out.println("There is no [ " + item + " ] in your inventory");
            }
        }
        else if (game.player.items.size() > 0) {
            int check = 0;
            for (int i = 0; i < game.player.items.size(); i++) {
                if (item.equals(game.player.items.get(i).name)) {
                    check += 1;
                    game.currentRoom.setItems(game.player.items.get(i));
                    game.player.items.remove(game.player.items.get(i));
                    System.out.println("[ "+ item + " ] was dropped!!");
                    break;
                }
            }
            if (check == 0) {
                System.out.println("There is no [ " + item + " ] in your inventory.");
            }
        }
        else {
            System.out.println("Your inventory is empty!!");
        }
    }
}
