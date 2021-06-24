package zork.commands;

import zork.Game;
import zork.object.item.Item;

import java.util.List;
import java.util.Scanner;

public class InventoryCommand implements Command{
    @Override
    public int numArgs() {
        return 0;
    }

    @Override
    public String getCommand() {
        return "inventory";
    }

    @Override
    public void execute(Game game, List<String> argument) {
        if (game.getGameStatus() == game.PLAY_STATUS) {
            Scanner in = new Scanner(System.in);
            if (game.player.items.size() <= 0) {
                System.out.println(" || INVENTORY || -> [ Empty ] ");
            } else {
                String x = " || INVENTORY || -> [ " + game.player.items.get(0).name;
                for (int i = 1; i < game.player.items.size(); i++) {
                    x = x + " , " + game.player.items.get(0).name;
                }
                System.out.println(x + " ]");
                System.out.println("Type [ back to game ] to continue playing");
                boolean checkTrue = false;
                while (checkTrue == false) {
                String inventory = in.nextLine();
                    for (Item item : game.player.items) {
                        if (inventory.equals("use " + item.name)) {
                            game.player.increaseHP(item.addHP);
                            System.out.println(item.skill);
                            System.out.println("HP : " + game.player.HP);
                            game.player.items.remove(item);
                            checkTrue = true;
                            break;
                        } else if (inventory.equals("back to game")) {
                            System.out.println("CONTINUE PLAYING");
                            checkTrue = true;
                        } else if (inventory.equals("use") || inventory.equals(item.name)) {
                            System.out.println("TYPE -> [ use {item name} ]");
                            checkTrue = false;
                        } else {
                            System.out.println("There is no " + inventory + " in this inventory.");
                            break;
                        }
                    }
                }
            }
        }
        else {
            System.out.println("!!Cannot use this command in start menu!!");
        }
    }
}
