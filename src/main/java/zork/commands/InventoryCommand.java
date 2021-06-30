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
        if (Game.getGameStatus() == Game.PLAY_STATUS) {
            Scanner in = new Scanner(System.in);
            if (Game.player.items.size() <= 0) {
                System.out.println(" || INVENTORY || -> [ Empty ] ");
            } else {
                StringBuilder x = new StringBuilder(" || INVENTORY || -> [ " + Game.player.items.get(0).name);
                for (int i = 1; i < Game.player.items.size(); i++) {
                    x.append(" , ").append(Game.player.items.get(0).name);
                }
                boolean checkTrue = false;
                while (!checkTrue) {
                System.out.println(x + " ]");
                System.out.println("Type [ back to game ] to continue playing");
                String inventory = in.nextLine();
                    for (Item item : Game.player.items) {
                        if (inventory.equals("use " + item.name)) {
                            Game.player.increaseHP(item.addHP);
                            System.out.println(item.skill);
                            System.out.println("HP : " + Game.player.HP);
                            Game.player.items.remove(item);
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
